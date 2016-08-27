package com.jacksen.sharelibrary;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by Admin on 2016/8/24.
 */
public class PlatformConfigHelper {

    private static final String _ID = "APP_ID";

    private static final String _SECRET = "APP_SECRET";

    private static HashMap<String, String> CONFIG = new HashMap<>();

    static void configPlatform(@NonNull String appId, @NonNull String appSecret) {
        if (!CONFIG.isEmpty()) {
            return;
        }
        CONFIG.put(_ID, appId);
        CONFIG.put(_SECRET, appSecret);
    }

    public static String getAppId() {
        return CONFIG.get(_ID);
    }
}
