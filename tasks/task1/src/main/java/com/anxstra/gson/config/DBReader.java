package com.anxstra.gson.config;

import com.anxstra.entities.DBFile;
import com.anxstra.entities.Setting;
import com.anxstra.exceptions.FieldIsMissingException;
import com.anxstra.exceptions.RequiredFileIsMissing;
import com.anxstra.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;

import static com.anxstra.gson.config.PathConstants.BASE_PATH_TO_FILE;
import static com.anxstra.gson.config.PathConstants.DB_FILE_NAME;
import static com.anxstra.gson.config.PathConstants.DB_FILE_ROOT_NAME;
import static com.anxstra.gson.config.PathConstants.DEPARTMENT_FILE_PREFIX;
import static com.anxstra.gson.config.PathConstants.FILE_EXTENSION;

public class DBReader {

    private DBReader() {
    }

    private static JsonObject getDBRoot() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(BASE_PATH_TO_FILE + DB_FILE_NAME))) {
            return GsonUtils.deserialize(reader, JsonObject.class);
        } catch (NoSuchFileException exception) {
            throw new FieldIsMissingException("db.json file cannot be found");
        }
    }

    public static JsonArray getJsonArray(String propertyName) {
        try {
            return getDBRoot().getAsJsonObject(DB_FILE_ROOT_NAME)
                              .getAsJsonArray(propertyName);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(0);
        }
        return null;
    }

    public static void initDB() {
        try {
            Setting setting = AppConfigurer.getSetting();
            JsonObject root = getDBRoot();
            if (Objects.isNull(root)) {
                root = new JsonObject();
                root.add(DB_FILE_ROOT_NAME, GsonConfigurer.getGson()
                                                          .toJsonTree(new DBFile()));
                for (String department : setting.getUseDepartments()) {
                    String path = DEPARTMENT_FILE_PREFIX + department + FILE_EXTENSION;
                    transferFromDepartment(root, path);
                }
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(0);
        }
    }

    private static void transferFromDepartment(JsonObject dbFile, String departmentPath) throws IOException {
        JsonObject dbRoot = dbFile.getAsJsonObject(DB_FILE_ROOT_NAME);
        JsonObject departmentFile;
        InputStream inputStream = Thread.currentThread()
                                        .getContextClassLoader()
                                        .getResourceAsStream(departmentPath);
        if (Objects.isNull(inputStream)) {
            throw new RequiredFileIsMissing("DB file cannot be found on path " + departmentPath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            departmentFile = GsonUtils.deserialize(reader, JsonObject.class);
        }

        JsonObject departmentRoot = departmentFile.getAsJsonObject(DB_FILE_ROOT_NAME);
        departmentRoot.entrySet()
                      .forEach(entry -> moveRecords(entry.getValue()
                                                         .getAsJsonArray(), dbRoot.getAsJsonArray(entry.getKey())));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(BASE_PATH_TO_FILE + departmentPath))) {
            GsonUtils.serialize(departmentFile, writer);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(BASE_PATH_TO_FILE + DB_FILE_NAME))) {
            GsonUtils.serialize(dbFile, writer);
        }
    }

    private static void moveRecords(JsonArray from, JsonArray to) {
        int i = 0;
        while (i < from.size()) {
            to.add(from.remove(i));
        }
    }
}
