package com.android.ifarm.ifarming.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import com.android.ifarm.ifarming.app.iFarmingApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isValidPhoneNumber(String input) {
        if (input == null) {
            return false;
        }

        if (input.length() != 11) {
            return false;
        }

        if (!input.startsWith("1")) {
            return false;
        }

        return true;
    }


    public static void restartApp() {
        iFarmingApp context = iFarmingApp.getInstance();
        Intent startIntent = context.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(context.getBaseContext().getPackageName());
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        AlarmManager alarmManager = (AlarmManager) iFarmingApp.getInstance().getSystemService(Service.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 500,
                PendingIntent.getActivity(iFarmingApp.getInstance(), (int) System.currentTimeMillis(),
                        startIntent, PendingIntent.FLAG_CANCEL_CURRENT));

        System.exit(0);
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
