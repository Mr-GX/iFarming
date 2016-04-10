package com.android.ifarm.ifarming.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用RecyclerView的Adapter
 */

public abstract class BaseRecyclerAdapter<E extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<E> {

    public List<T> data = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(final E viewHolder, final int position) {
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder, position);
                }
            });
        }
    }

    public T getItem(int position) {
        return data == null || data.size() == 0 || position >= data.size() ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public List<T> getData() {
        return data;
    }
    public List<T> getItems(List<Integer> positions) {
        List<T> result = new ArrayList<T>();
        for (Integer position : positions) {
            result.add(getItem(position));
        }
        return result;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(T t) {
        if (this.data == null)
            this.data = new ArrayList<T>();
        this.data.add(t);
        notifyDataSetChanged();
    }

    public void addHeaderData(List<T> list) {
        if (this.data == null)
            this.data = new ArrayList<T>();
        this.data.addAll(0, list);
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        if (this.data == null)
            this.data = new ArrayList<T>();
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void update(int position, T t){
        if (this.data == null || position >= this.data.size() || position <0){
            return;
        }
        this.data.set(position, t);
        notifyItemChanged(position);
    }

    public void remove(T t) {
        data.remove(t);
        notifyDataSetChanged();
    }

    public void clear() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public static interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
    }
}
