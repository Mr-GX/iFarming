package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.adapter.HomeAdapter;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.ui.event.FarmEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    HomeAdapter adapter;

    public static HomeFragment newFragment() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bindView(this, view);
        setView();
        return view;
    }

    private void setView() {
        refreshLayout.setOnRefreshListener(mOnRefreshListener);
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_no_data,
                listView, false);
        listView.addHeaderView(headerView, null, false);
        empty = (TextView) headerView.findViewById(R.id.empty);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_search)
    void search() {
        if (TextUtils.isEmpty(searchContent.getText().toString())){
            Snackbar.make(searchContent, "搜索内容不能为空！", Snackbar.LENGTH_SHORT).setAction("我知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            return;
        }
        List<DicFarm> farms = new Select().from(DicFarm.class).where("DicName like '%"+searchContent.getText().toString()+"%'").execute();
        if (farms.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            adapter.clear();
        } else {
            empty.setVisibility(View.GONE);
            adapter.add(farms, true);
        }
    }

    @Bind(R.id.search_content)
    EditText searchContent;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.list)
    ListView listView;

    TextView empty;

    private void autoRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
        mOnRefreshListener.onRefresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        autoRefresh();
    }

    @Subscribe
    public void onEvent(FarmEvent event) {
        super.onEvent(event);
        autoRefresh();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus();
        adapter = new HomeAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }

    SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadDb();
        }
    };

    private void loadDb() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 600);
        List<DicFarm> farms = new Select().from(DicFarm.class).execute();
        if (farms.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            adapter.clear();
        } else {
            empty.setVisibility(View.GONE);
            adapter.add(farms, true);
        }
    }

}
