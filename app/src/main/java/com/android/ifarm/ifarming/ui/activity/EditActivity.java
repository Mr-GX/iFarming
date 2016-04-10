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
import android.widget.TextView;

import com.activeandroid.query.Update;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.ui.db.DicUser;
import com.android.ifarm.ifarming.util.Utils;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class EditActivity extends BaseActivity {

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
//        StatusBarUtil.setTranslucent(this);
        bindView(this);
        setView();
    }

    private void setView() {
        Glide.with(this).load(AppConfig.getAvatar()).error(R.mipmap.logo_main).into(cover);
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
        AppConfig.setAvatar(mUri != null ? mUri.toString() : AppConfig.getAvatar());
        AppConfig.setRealName(name.getText().toString());
        AppConfig.setEmail(email.getText().toString());
        AppConfig.setMobile(phone.getText().toString());
        new Update(DicUser.class).set("DicName=?," + "DicEmail=?," + "DicMobile=?," + "DicCover=?", name.getText().toString(), email.getText().toString(), phone.getText().toString(), mUri != null ? mUri.toString() : AppConfig.getAvatar()).where("DicUid=?", AppConfig.getUserId()).execute();
        setResult(RESULT_OK, new Intent());
        finish();
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
        if (resultCode != RESULT_OK || data == null) {
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
            Glide.with(this).load(mUri).error(R.mipmap.logo_main).into(cover);
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
