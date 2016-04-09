package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import butterknife.Bind;

public class BasicInfoFragment extends BaseFragment {

    public static BasicInfoFragment newFragment(DicFarm farm) {
        BasicInfoFragment fragment = new BasicInfoFragment();
        Bundle args = new Bundle();
        args.putLong("id", farm.dicCode);
        args.putString("avatar", farm.dicAvatar);
        args.putString("name", farm.dicName);
        args.putString("address", farm.dicAddress);
        args.putString("contact", farm.dicContact);
        args.putString("mobile", farm.dicMobile);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);
        bindView(this, view);
        return view;
    }

    @Bind(R.id.cover)
    CircleImageView cover;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.contact)
    TextView contact;
    @Bind(R.id.mobile)
    TextView mobile;
    String farmAvatar, farmName, farmAddress, farmContact, farmMobile;
    long farmId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadShar();
    }

    private void loadShar() {
        Glide.with(getActivity()).load(farmAvatar).error(R.mipmap.logo_main).into(cover);
        name.setText(farmName);
        address.setText(farmAddress);
        contact.setText(farmContact);
        mobile.setText(farmMobile);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
        farmId = args.getLong("id", 0);
        farmAvatar = args.getString("avatar", "");
        farmName = args.getString("name", "");
        farmAddress = args.getString("address", "");
        farmContact = args.getString("contact", "");
        farmMobile = args.getString("mobile", "");
    }

}
