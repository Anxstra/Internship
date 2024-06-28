package com.anxstra.utils;

import com.anxstra.gson.config.GsonConfigurer;
import com.google.gson.JsonElement;

import java.io.Reader;

public class GsonUtils {

    private GsonUtils() {}

    public static <T> T deserialize(JsonElement jsonElement, Class<T> classOfT) {
        return GsonConfigurer.getGson().fromJson(jsonElement, classOfT);
    }

    public static <T> T deserialize(Reader json, Class<T> classOfT) {
        return GsonConfigurer.getGson().fromJson(json, classOfT);
    }

    public static void serialize(JsonElement jsonElement, Appendable writer) {
        GsonConfigurer.getGson().toJson(jsonElement, writer);
    }
}
