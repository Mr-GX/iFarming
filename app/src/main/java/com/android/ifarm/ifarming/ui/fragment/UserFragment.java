package com.android.ifarm.ifarming.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.app.AppConfig;
import com.android.ifarm.ifarming.ui.activity.LoginActivity;
import com.android.ifarm.ifarming.ui.adapter.HomeAdapter;
import com.android.ifarm.ifarming.widget.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class UserFragment extends BaseFragment {

    public static UserFragment newFragment() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        bindView(this,view);
        return view;
    }

    @Bind(R.id.cover)
    CircleImageView cover;
    @Bind(R.id.uid)
    TextView uid;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.phone)
    TextView phone;

    @OnClick(R.id.edit)
    void edit() {

    }

    @OnClick(R.id.quit)
    void quit() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle(R.string.hint).setCancelable(true)
                .setMessage(R.string.sure_to_quit).setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doLogout();
                    }
                }).create();
        dialog.show();
    }

    private void doLogout() {
        AppConfig.quit();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadShar();
    }

    private void loadShar() {
        uid.setText(String.format("编号 %s",AppConfig.getUserId()));
        name.setText(AppConfig.getRealName());
        email.setText(AppConfig.getEmail());
        phone.setText(AppConfig.getMobile());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeAdapter adapter = new HomeAdapter();
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
    }
}
