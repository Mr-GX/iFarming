package com.android.ifarm.ifarming.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.ifarm.ifarming.R;
import com.bumptech.glide.Glide;

public class SarsPhotosAdapter extends BaseRecyclerAdapter<SarsPhotosAdapter.ViewHolder, String> {

    private Callback callback;

    public SarsPhotosAdapter() {
    }

    public SarsPhotosAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_sars_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        super.onBindViewHolder(viewHolder, position);
        viewHolder.setIsRecyclable(false);
        String info = data.get(position);
        if (!((Activity) viewHolder.cover.getContext()).isFinishing()) {
            Glide.with(viewHolder.cover.getContext()).load(info).placeholder(R.mipmap.logo_main).error(R.mipmap.logo_main).centerCrop().into(viewHolder.cover);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;

        public ViewHolder(View view) {
            super(view);
            cover = (ImageView) view.findViewById(R.id.iv_cover);
        }

    }

    public interface Callback {
        void op();
    }
}

