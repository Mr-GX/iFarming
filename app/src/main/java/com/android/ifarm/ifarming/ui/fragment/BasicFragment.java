package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.android.ifarm.ifarming.R;

import butterknife.Bind;

public class BasicFragment extends BaseFragment {
    FragmentAdapter adapter;

    public static BasicFragment newFragment() {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        bindView(this, view);
        return view;
    }

    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.radio_group_basic)
    RadioGroup mRadioGroup;
    static final int[] RADIO_IDS = {R.id.radio_place, R.id.radio_anim};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new FragmentAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioGroup.check(RADIO_IDS[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                BasicPlaceFragment fragment = new BasicPlaceFragment();
                return fragment;
            } else {
                BasicAnimFragment fragment = new BasicAnimFragment();
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
