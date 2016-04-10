package com.android.ifarm.ifarming.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicSars;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
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
}
