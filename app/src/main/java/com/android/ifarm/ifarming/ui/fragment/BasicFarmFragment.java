package com.android.ifarm.ifarming.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.ui.event.FarmEvent;
import com.android.ifarm.ifarming.util.Utils;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BasicFarmFragment extends BaseFragment {

    private Uri mUri;

    public static BasicFarmFragment newFragment() {
        BasicFarmFragment fragment = new BasicFarmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_place, container, false);
        bindView(this, view);
        return view;
    }

    @OnClick(R.id.cover)
    void onAvatar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setItems(new String[]{
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

    @Bind(R.id.cover)
    CircleImageView mCover;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.place)
    EditText mPlace;
    @Bind(R.id.people)
    EditText mPeople;
    @Bind(R.id.mobile)
    EditText mMobile;

    @OnClick(R.id.save)
    void onSave() {
        if (mUri == null) {
            Snackbar.make(mCover, "养殖场图片不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mName.getText().toString())) {
            Snackbar.make(mName, "养殖场名称不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mPlace.getText().toString())) {
            Snackbar.make(mPlace, "养殖场地址不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mPeople.getText().toString())) {
            Snackbar.make(mPeople, "养殖场联系人不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        if (TextUtils.isEmpty(mMobile.getText().toString()) || !Utils.isValidPhoneNumber(mMobile.getText().toString())) {
            Snackbar.make(mMobile, "联系人电话为空或不合法！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicFarm> farms = new Select().from(DicFarm.class).where("DicMobile= ?", mMobile.getText().toString()).execute();
        if (farms.size() > 0) {
            Snackbar.make(mMobile, "联系人电话已经存在！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicFarm> farms1 = new Select().from(DicFarm.class).execute();
        DicFarm farm = new DicFarm(mUri.toString(), mName.getText().toString(), mPlace.getText().toString(), mPeople.getText().toString(), mMobile.getText().toString(), farms1.size() + 1, AppConfig.getUserId());
        farm.save();
        mUri = null;
        Glide.with(this).load("").error(R.mipmap.logo_main).into(mCover);
        mName.setText(null);
        mPlace.setText(null);
        mPeople.setText(null);
        mMobile.setText(null);
        Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT).show();
        postEvent(new FarmEvent());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    private void onCamera() {
        try {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(getActivity().getExternalCacheDir(),
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK || data == null) {
            return;
        }

        if (requestCode == 2000) {
            Uri uri = data.getData();
            onCrop(uri);
        } else if (requestCode == 3000) {
            Uri uri = data.getData();
            if (uri == null) {
                uri = currentPng;
            }
            mUri = uri;
            Glide.with(this).load(mUri).error(R.mipmap.logo_main).into(mCover);
        } else if (requestCode == 1000) {
            onCrop(Uri.fromFile(new File(getActivity().getExternalCacheDir(), "camera.jpeg")));
        }
    }

    Uri currentPng;

    private void onCrop(Uri uri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
        currentPng = Uri.fromFile(new
                File(getActivity().getExternalCacheDir(), "crop_image_out_farm" + System.currentTimeMillis() + ".jpg"));
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPng);
        cropIntent.putExtra("outputX", 400);
        cropIntent.putExtra("outputY", 400);
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(cropIntent, 3000);
    }
}
