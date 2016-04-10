package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.adapter.SarsAdapter;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.ui.db.DicSars;

import java.util.List;

import butterknife.Bind;

public class SarsInfoFragment extends BaseFragment {
    SarsAdapter adapter;

    public static SarsInfoFragment newFragment(DicFarm farm) {
        SarsInfoFragment fragment = new SarsInfoFragment();
        Bundle args = new Bundle();
        args.putLong("id", farm.dicCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sars_info, container, false);
        bindView(this, view);
        listView.setAdapter(adapter);
        return view;
    }

    @Bind(R.id.list)
    ListView listView;
    long farmId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<DicSars> execute = new Select().from(DicSars.class).where("DicCode=?", farmId).execute();
        adapter.add(execute);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SarsAdapter();
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
        farmId = args.getLong("id", 0);
    }

}
