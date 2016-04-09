package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BasicFarmFragment extends BaseFragment {

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
        DicFarm farm = new DicFarm("", mName.getText().toString(), mPlace.getText().toString(), mPeople.getText().toString(), mMobile.getText().toString(), farms1.size() + 1, AppConfig.getUserId());
        farm.save();
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
}
