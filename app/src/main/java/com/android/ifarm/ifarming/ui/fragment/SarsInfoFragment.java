package com.android.ifarm.ifarming.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.activity.InfoActivity;
import com.android.ifarm.ifarming.ui.activity.SarsActivity;
import com.android.ifarm.ifarming.ui.adapter.SarsAdapter;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.ui.db.DicSars;

import java.util.List;

import butterknife.Bind;

public class SarsInfoFragment extends BaseFragment implements AdapterView.OnItemClickListener {
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
        setView();
        return view;
    }

    private void setView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_no_data,
                listView, false);
        listView.addHeaderView(headerView, null, false);
        empty = (TextView) headerView.findViewById(R.id.empty);
        empty.setText("暂未添加疫情信息，快去添加吧");
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Bind(R.id.list)
    ListView listView;
    long farmId;
    TextView empty;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<DicSars> execute = new Select().from(DicSars.class).where("DicCode=?", farmId).execute();
        if (execute.size()==0){
            empty.setVisibility(View.VISIBLE);
            adapter.clear();
        }else {
            empty.setVisibility(View.GONE);
            adapter.add(execute);
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position -= ((ListView) parent).getHeaderViewsCount();
        DicSars sars = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), SarsActivity.class);
        intent.putExtra(InfoActivity.PARAM, sars);
        startActivity(intent);
    }
}
