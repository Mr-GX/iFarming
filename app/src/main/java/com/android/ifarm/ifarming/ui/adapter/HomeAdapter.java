package com.android.ifarm.ifarming.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    private ArrayList<String> mDatas;

    public HomeAdapter() {
        mDatas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDatas.size() > 0 ? mDatas.size() : 0;
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void addActivityInfos(ArrayList<String> datas, boolean clear) {
        if (clear) {
            mDatas.clear();
        }

        if (datas != null) {
            mDatas.addAll(datas);
        }

        notifyDataSetChanged();
    }

    @Override
    public String getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home,
                    parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.title = (TextView) convertView.findViewById(R.id.title);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String data = mDatas.get(position);
        holder.title.setText(data);
        return convertView;
    }


    static class ViewHolder {
        TextView title;
    }
}
