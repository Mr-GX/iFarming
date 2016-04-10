package com.android.ifarm.ifarming.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicSars;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SarsAdapter extends BaseAdapter {
    private List<DicSars> mDatas;

    public SarsAdapter() {
        mDatas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDatas.size() > 0 ? mDatas.size() : 0;
    }

    public void add(List<DicSars> datas) {
        mDatas.clear();
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public DicSars getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sars,
                    parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.avatar = (CircleImageView) convertView.findViewById(R.id.sars_avatar);
            holder.code = (TextView) convertView.findViewById(R.id.sars_code);
            holder.pz = (TextView) convertView.findViewById(R.id.sars_pz);
            holder.time = (TextView) convertView.findViewById(R.id.sars_time);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DicSars data = mDatas.get(position);
        if (!((Activity) holder.avatar.getContext()).isFinishing()) {
            Glide.with(holder.avatar.getContext()).load(data.dicPic.split(",")[0]).error(R.mipmap.logo_main).into(holder.avatar);
        }
        holder.code.setText(String.format("#%s", data.dicNum));
        holder.pz.setText(data.dicPz);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data.dicTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:MM");
        String date = simpleDateFormat.format(calendar.getTime());
        holder.time.setText(date);
        return convertView;
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }


    static class ViewHolder {
        CircleImageView avatar;
        TextView code, pz, time;
    }
}
