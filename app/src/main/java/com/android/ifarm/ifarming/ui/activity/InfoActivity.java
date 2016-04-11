package com.android.ifarm.ifarming.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.db.DicFarm;
import com.android.ifarm.ifarming.ui.fragment.AnimInfoFragment;
import com.android.ifarm.ifarming.ui.fragment.BasicInfoFragment;
import com.android.ifarm.ifarming.ui.fragment.SarsInfoFragment;
import com.jaeger.library.StatusBarUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity {
    FragmentAdapter adapter;
    public static final String PARAM = "Param";
    DicFarm farm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        StatusBarUtil.setTranslucent(this);
        bindView(this);
        setViewPager();
    }

    private void setViewPager() {
        farm = (DicFarm) getIntent().getSerializableExtra(PARAM);
        title.setText(String.format("No.%s", farm.dicCode));
        adapter = new FragmentAdapter(getSupportFragmentManager());
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

    @OnClick(R.id.btn_back)
    void back() {
        finish();
    }

    @Bind(R.id.btn_title)
    TextView title;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;
    static final int[] RADIO_IDS = {R.id.radio_basic, R.id.radio_anim, R.id.radio_sars};

    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return BasicInfoFragment.newFragment(farm);
            } else if (position == 1) {
                return AnimInfoFragment.newFragment(farm);
            } else {
                return SarsInfoFragment.newFragment(farm);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
