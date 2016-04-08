package com.android.ifarm.ifarming.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    private static SharedPreferences preferences;

    static {
        preferences = iFarmingApp.getInstance().getSharedPreferences(
                "user", Context.MODE_PRIVATE);
    }

    public static synchronized long getUserId() {
        return preferences.getLong("user_id", 0);
    }

    public static synchronized void setUserId(long user_id) {
        preferences.edit().putLong("user_id", user_id).apply();
    }

    public static String getEmail() {
        return preferences.getString("email", null);
    }

    public static void setEmail(String email) {
        preferences.edit().putString("email", email).apply();
    }

    public static synchronized String getMobile() {
        return preferences.getString("mobile", null);
    }

    public static synchronized void setMobile(String mobile) {
        preferences.edit().putString("mobile", mobile).apply();
    }

    public static synchronized String getRealName() {
        return preferences.getString("real_name", null);
    }

    public static synchronized void setRealName(String real_name) {
        preferences.edit().putString("real_name", real_name).apply();
    }

    public static synchronized boolean isLogin() {
        return getUserId() != 0;
    }

    public static synchronized void quit() {
        preferences.edit().clear().apply();
    }
}
