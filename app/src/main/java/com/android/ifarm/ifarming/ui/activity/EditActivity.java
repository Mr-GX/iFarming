package com.android.ifarm.ifarming.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.util.Utils;
import com.android.ifarm.ifarming.widget.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class EditActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
//        StatusBarUtil.setTranslucent(this);
        bindView(this);
        setView();
    }

    private void setView() {
        uid.setText(String.format("编号 %s", AppConfig.getUserId()));
        name.setText(AppConfig.getRealName());
        email.setText(AppConfig.getEmail());
        phone.setText(AppConfig.getMobile());
    }

    @Bind(R.id.cover)
    CircleImageView cover;
    @Bind(R.id.uid)
    TextView uid;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.phone)
    EditText phone;

    @OnClick(R.id.cover)
    void onAvatar() {

    }

    @OnClick(R.id.save)
    void save() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            Snackbar.make(name, "姓名不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(email.getText().toString()) || !Utils.isEmail(email.getText().toString())) {
            Snackbar.make(name, "邮箱为空或不合法！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(phone.getText().toString()) || !Utils.isValidPhoneNumber(phone.getText().toString())) {
            Snackbar.make(name, "联系方式为空或不合法！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        AppConfig.setAvata("");
        AppConfig.setRealName(name.getText().toString());
        AppConfig.setEmail(email.getText().toString());
        AppConfig.setMobile(phone.getText().toString());
//        new Update(DicUser.class).set("DicName=?," + "DicEmail=?" + "DicMobile=?" + "DicCover=?", name.getText().toString(), email.getText().toString(), phone.getText().toString(), "").where("DicUid=?", AppConfig.getUserId()).execute();
        setResult(RESULT_OK,new Intent());
        finish();
    }

}
