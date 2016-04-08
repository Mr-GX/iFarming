package com.android.ifarm.ifarming.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.widget.CircleImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private List<DicFarm> mDatas;

    public HomeAdapter() {
        mDatas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDatas.size() > 0 ? mDatas.size() : 0;
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void add(List<DicFarm> datas, boolean clear) {
        if (clear) {
            mDatas.clear();
        }
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public DicFarm getItem(int i) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home,
                    parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.avatar = (CircleImageView) convertView.findViewById(R.id.farm_avatar);
            holder.code = (TextView) convertView.findViewById(R.id.farm_code);
            holder.name = (TextView) convertView.findViewById(R.id.farm_name);
            holder.address = (TextView) convertView.findViewById(R.id.farm_address);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DicFarm data = mDatas.get(position);
        if (!((Activity)holder.avatar.getContext()).isFinishing()){
            Glide.with(holder.avatar.getContext()).load(data.dicAvatar).error(R.mipmap.logo_main).into(holder.avatar);
        }
        holder.code.setText(String.format("No.%s",data.dicCode));
        holder.name.setText(data.dicName);
        holder.address.setText(data.dicAddress);
        return convertView;
    }


    static class ViewHolder {
        CircleImageView avatar;
        TextView code,name,address;
    }
}
