package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.adapter.AnimAdapter;
import com.android.ifarm.ifarming.ui.db.DicAnim;
import com.android.ifarm.ifarming.ui.db.DicFarm;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class AnimInfoFragment extends BaseFragment {

    public static AnimInfoFragment newFragment(DicFarm farm) {
        AnimInfoFragment fragment = new AnimInfoFragment();
        Bundle args = new Bundle();
        args.putLong("id", farm.dicCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anim_info, container, false);
        bindView(this, view);
        setView();
        return view;
    }

    private void setView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_no_data,
                listView.getWrappedList(), false);
        listView.getWrappedList().addHeaderView(headerView, null, false);
        empty = (TextView) headerView.findViewById(R.id.empty);
        empty.setText("暂未添加动物信息，快去添加吧");
        listView.setAdapter(adapter);
    }

    long farmId;
    @Bind(R.id.list)
    StickyListHeadersListView listView;
    TextView empty;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<DicAnim> animsOfNiu = new Select().from(DicAnim.class).where("DicCode=? and DicType=?", farmId, "牛").execute();
        List<DicAnim> animsOfYang = new Select().from(DicAnim.class).where("DicCode=? and DicType=?", farmId, "羊").execute();
        List<DicAnim> animsOfZhu = new Select().from(DicAnim.class).where("DicCode=? and DicType=?", farmId, "猪").execute();
        List<DicAnim> animsOfJi = new Select().from(DicAnim.class).where("DicCode=? and DicType=?", farmId, "鸡").execute();
        List<DicAnim> all = new ArrayList<>();
        all.addAll(animsOfNiu);
        all.addAll(animsOfYang);
        all.addAll(animsOfZhu);
        all.addAll(animsOfJi);
        if (all.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            adapter.clear();
        } else {
            empty.setVisibility(View.GONE);
            adapter.setData(all);
        }
    }

    AnimAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AnimAdapter(getActivity());
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
        farmId = args.getLong("id", 0);
    }

}
