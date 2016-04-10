package com.android.ifarm.ifarming.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.adapter.SarsPhotosAdapter;
import com.android.ifarm.ifarming.ui.db.DicSars;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

public class SarsActivity extends BaseActivity {

    DicSars sars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sars);
//        StatusBarUtil.setTranslucent(this);
        bindView(this);
        setView();
    }

    private void setView() {
        sars = (DicSars) getIntent().getSerializableExtra(InfoActivity.PARAM);
        title.setText(String.format("No.%s", sars.dicCode));
        Glide.with(this).load(sars.dicPic.split(",")[0]).error(R.mipmap.logo_main).into(cover);
        num.setText(String.format("#%s", sars.dicNum));
        pz.setText(sars.dicPz);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sars.dicTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:MM");
        String date = simpleDateFormat.format(calendar.getTime());
        time.setText(date);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        photos.setLayoutManager(linearLayoutManager);
        photos.setHasFixedSize(true);
        photos.removeItemDecoration(decoration);
        photos.addItemDecoration(decoration);
        SarsPhotosAdapter mAdapter = new SarsPhotosAdapter();
        mAdapter.setData(Arrays.asList(sars.dicPic.split(",")));
        photos.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_back)
    void back() {
        finish();
    }

    @Bind(R.id.btn_title)
    TextView title;
    @Bind(R.id.cover)
    CircleImageView cover;
    @Bind(R.id.num)
    TextView num;
    @Bind(R.id.pz)
    TextView pz;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.recycler_view)
    RecyclerView photos;

    RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.right = 20;
        }
    };
}
