package com.android.ifarm.ifarming.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.android.ifarm.ifarming.R;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView(this);
    }

    @Bind(R.id.edit_name)
    EditText mName;
    @Bind(R.id.edit_password)
    EditText mPwd;

    @OnClick(R.id.btn_login)
    public void login() {
        if (!TextUtils.isEmpty(mName.getText().toString()) && !TextUtils.isEmpty(mPwd.getText().toString())) {
            Intent intent=new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
