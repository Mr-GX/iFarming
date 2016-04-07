package com.android.ifarm.ifarming.app;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.bumptech.glide.Glide;

public class iFarmingApp extends Application {
    private static iFarmingApp mContext;

    public static Context getContext() {
        return mContext;
    }
    public static iFarmingApp getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Configuration.Builder configurationBuilder = new Configuration.Builder(getApplicationContext());
        configurationBuilder.addModelClasses(
//                DicSyncType.class,
//                DicGroupType.class
        );
        ActiveAndroid.initialize(configurationBuilder.create());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).clearMemory();
    }
}
