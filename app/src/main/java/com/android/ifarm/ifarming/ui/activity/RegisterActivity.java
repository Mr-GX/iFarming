package com.android.ifarm.ifarming.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.EditText;

import com.android.ifarm.ifarming.R;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        if (!TextUtils.isEmpty(mName.getText().toString()) && !TextUtils.isEmpty(mEmail.getText().toString()) && !TextUtils.isEmpty(mMobile.getText().toString()) && !TextUtils.isEmpty(mPwd.getText().toString())) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
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
                uri = Uri.fromFile(new
                        File(getExternalCacheDir(), "crop_image_out.jpg"));
            }
            mUri = uri;
        } else if (requestCode == 1000) {
            onCrop(Uri.fromFile(new File(getExternalCacheDir(), "camera.jpeg")));
        }

    }

    private void onCrop(Uri uri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new
                File(getExternalCacheDir(), "crop_image_out.jpg")));
        cropIntent.putExtra("outputX", 400);
        cropIntent.putExtra("outputY", 400);
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(cropIntent, 3000);
    }
}
