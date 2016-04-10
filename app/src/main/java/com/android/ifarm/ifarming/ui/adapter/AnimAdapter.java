package com.android.ifarm.ifarming.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicAnim;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class AnimAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private LayoutInflater inflater;
    List<DicAnim> all;

    public AnimAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        all=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return all.size();
    }

    @Override
    public DicAnim getItem(int position) {
        return all.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_anim, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.count = (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(all.get(position).dicPz);
        holder.count.setText(all.get(position).dicCount);
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_item_anim_header, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.seri = (TextView) convertView.findViewById(R.id.seri);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        if (getHeaderId(position) == 1) {
            holder.title.setText("牛");
            holder.seri.setText("#0123");
        } else if (getHeaderId(position) == 2) {
            holder.title.setText("羊");
            holder.seri.setText("#0124");
        } else if (getHeaderId(position) == 3) {
            holder.title.setText("猪");
            holder.seri.setText("#0125");
        } else if (getHeaderId(position) == 4) {
            holder.title.setText("鸡");
            holder.seri.setText("#0126");
        } else {
            holder.title.setText("");
            holder.seri.setText("");
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if (all.get(position).dicType.equals("牛")) {
            return 1;
        } else if (all.get(position).dicType.equals("羊")) {
            return 2;
        } else if (all.get(position).dicType.equals("猪")) {
            return 3;
        } else if (all.get(position).dicType.equals("鸡")) {
            return 4;
        } else {
            return 5;
        }
    }

    public void setData(List<DicAnim> all) {
        this.all = all;
        notifyDataSetChanged();
    }

    public void clear() {
        all.clear();
        notifyDataSetChanged();
    }

    class HeaderViewHolder {
        TextView title, seri;
    }

    class ViewHolder {
        TextView title, count;
    }
}
