package com.android.ifarm.ifarming.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.ui.db.DicUser;
import com.android.ifarm.ifarming.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppConfig.isLogin()) {
            startActivity(new Intent(this, MainActivity.class));
            LoginActivity.this.finish();
            return;
        }
        setContentView(R.layout.activity_login);
//        StatusBarUtil.setTranslucent(this);
        bindView(this);
    }

    @Bind(R.id.edit_name)
    EditText mName;
    @Bind(R.id.edit_password)
    EditText mPwd;

    @OnClick(R.id.btn_login)
    public void login() {
        if (TextUtils.isEmpty(mName.getText().toString()) || !Utils.isValidPhoneNumber(mName.getText().toString()) || TextUtils.isEmpty(mPwd.getText().toString())) {
            Snackbar.make(mName, "用户名和密码不合法或空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicUser> users = new Select().from(DicUser.class).where("DicMobile= ?", mName.getText().toString()).orderBy("DicUid ASC").execute();
        if (users.size() == 0) {
            Snackbar.make(mName, "用户不存在！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        } else {
            if (users.get(0).dicPwd.equals(mPwd.getText().toString())) {
                AppConfig.setUserId(users.get(0).dicUid);
                AppConfig.setRealName(users.get(0).dicName);
                AppConfig.setEmail(users.get(0).dicEmail);
                AppConfig.setMobile(users.get(0).dicMobile);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Snackbar.make(mPwd, "密码错误！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        }
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
