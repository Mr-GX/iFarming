package com.android.ifarm.ifarming.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.util.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageContainer extends ViewGroup {

    static final int IMAGE_SIZE = 90;
    static final int MARGIN = 6;

    int mMargin;
    int mImageSize;

    ArrayList<String> mUrls;

    public ImageContainer(Context context) {
        super(context);
        init();
    }

    public ImageContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        mUrls = new ArrayList<>();
        mMargin = Utils.dp2px(getContext(), MARGIN);
        mImageSize = Utils.dp2px(getContext(), IMAGE_SIZE);
    }

    private boolean isSameUrls(ArrayList<String> urls) {
        if (urls.size() == mUrls.size()) {
            for (String url : mUrls) {
                if (!urls.contains(url)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void setImages(ArrayList<String> urls) {
        if (urls == null || isSameUrls(urls)) {
            return;
        }

        while (urls.size() < getChildCount()) {
            removeViewAt(getChildCount() - 1);
        }

        for (int i = getChildCount(); i < urls.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            addView(imageView);
        }

        for (int i = 0; i < urls.size(); i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (!((Activity)imageView.getContext()).isFinishing()){
                Glide.with(imageView.getContext()).load(urls.get(i)).error(R.mipmap.logo_main).placeholder(R.mipmap.logo_main).into(imageView);
            }
        }
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int l = 0;
        int t = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View view = getChildAt(i);
            if (l + mImageSize > getMeasuredWidth()) {
                l = 0;
                t += (mImageSize + mMargin);
            }
            view.layout(l, t, l + view.getMeasuredWidth(), t + view.getMeasuredHeight());
            l += (mImageSize + mMargin);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() == 0) {
            super.onMeasure(widthMeasureSpec, 0);
            return;
        }

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height;
        int needsChildWidth = (getChildCount() - 1) * mMargin + getChildCount() * mImageSize;
        if (needsChildWidth > width) {
            height = mImageSize * 2 + mMargin;
        } else {
            height = mImageSize;
        }

        for (int i = 0; i < getChildCount(); i++) {
            final View view = getChildAt(i);
            view.measure(mImageSize | MeasureSpec.EXACTLY, mImageSize | MeasureSpec.EXACTLY);
        }

        setMeasuredDimension(width, height);
    }
}
