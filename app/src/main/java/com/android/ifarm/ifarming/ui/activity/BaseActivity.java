package com.android.ifarm.ifarming.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public void bindView(Activity activity) {
        ButterKnife.bind(activity);
    }

//    public void registerEventBus() {
//        EventBus.getDefault().register(this);
//    }
//
//    public void unRegisterEventBus() {
//        EventBus.getDefault().unregister(this);
//    }
//
//    public void postEvent(Object event) {
//        EventBus.getDefault().post(event);
//    }
//
//    public void onEvent(Object event) {
//    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
