package com.anxstra.gson.config;

import java.io.File;

public class PathConstants {

    static final String SETTINGS_FILE_ROOT_NAME = "settings";

    static final String DB_FILE_ROOT_NAME = "data";

    static final String BASE_PATH_TO_FILE = "target" + File.separator + "data" + File.separator;

    static final String DB_FILE_NAME = "db.json";

    static final String DEPARTMENT_FILE_PREFIX = "db_";

    static final String SETTING_FILE_NAME = "settings.json";

    static final String FILE_EXTENSION = ".json";

    private PathConstants() {
    }
}
