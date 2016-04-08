package com.android.ifarm.ifarming.app;


import com.android.ifarm.ifarming.util.Utils;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHelper implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        Utils.restartApp();
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

}
