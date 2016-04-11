package com.android.ifarm.ifarming.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.ui.db.DicUser;
import com.android.ifarm.ifarming.util.Utils;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setTranslucent(this);
        bindView(this);
    }

    @OnClick(R.id.add_photo)
    public void addPhoto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setItems(new String[]{
                        getString(R.string.new_picture), getString(R.string.pick_from_photo_lib)},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            onCamera();
                        } else if (i == 1) {
                            onGalery();
                        }
                    }
                });
        builder.create().show();
    }

    @Bind(R.id.add_photo)
    CircleImageView mCover;
    @Bind(R.id.edit_name)
    EditText mName;
    @Bind(R.id.edit_email)
    EditText mEmail;
    @Bind(R.id.edit_mobile)
    EditText mMobile;
    @Bind(R.id.edit_password)
    EditText mPwd;

    @OnClick(R.id.btn_register)
    public void register() {
        if (TextUtils.isEmpty(mName.getText().toString())) {
            Snackbar.make(mName, "请输入用户名！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mEmail.getText().toString()) || !Utils.isEmail(mEmail.getText().toString())) {
            Snackbar.make(mEmail, "邮箱为空或不合法！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mMobile.getText().toString()) || !Utils.isValidPhoneNumber(mMobile.getText().toString())) {
            Snackbar.make(mMobile, "手机号码为空或不合法！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicUser> users = new Select().from(DicUser.class).where("DicMobile= ?", mMobile.getText().toString()).orderBy("DicUid ASC").execute();
        if (users.size() > 0) {
            Snackbar.make(mMobile, "手机号码已经存在！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mPwd.getText().toString())) {
            Snackbar.make(mPwd, "密码不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicUser> users1 = new Select().from(DicUser.class).execute();
        DicUser user = new DicUser(mName.getText().toString(), mEmail.getText().toString(), mMobile.getText().toString(), mPwd.getText().toString(), users1.size() + 1, mUri != null ? mUri.toString() : "");
        user.save();
        AppConfig.setAvatar(mUri != null ? mUri.toString() : "");
        AppConfig.setRealName(mName.getText().toString());
        AppConfig.setEmail(mEmail.getText().toString());
        AppConfig.setMobile(mMobile.getText().toString());
        AppConfig.setUserId(users1.size() + 1);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void onCamera() {
        try {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(getExternalCacheDir(),
                    "camera.jpeg")));
            startActivityForResult(getImageByCamera, 1000);
        } catch (Exception e) {
        }
    }

    private void onGalery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK ) {
            return;
        }

        if (requestCode == 2000) {
            Uri uri = data.getData();
            onCrop(uri);
        } else if (requestCode == 3000) {
            Uri uri = data.getData();
            if (uri == null) {
                uri = currentAvatar;
            }
            mUri = uri;
            Glide.with(this).load(mUri).error(R.mipmap.logo_main).into(mCover);
        } else if (requestCode == 1000) {
            onCrop(Uri.fromFile(new File(getExternalCacheDir(), "camera.jpeg")));
        }
    }

    Uri currentAvatar;

    private void onCrop(Uri uri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
        currentAvatar = Uri.fromFile(new
                File(getExternalCacheDir(), "crop_image_out_user" + System.currentTimeMillis() + ".jpg"));
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentAvatar);
        cropIntent.putExtra("outputX", 400);
        cropIntent.putExtra("outputY", 400);
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(cropIntent, 3000);
    }
}
