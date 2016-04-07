package com.android.ifarm.ifarming.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.fragment.BasicFragment;
import com.android.ifarm.ifarming.ui.fragment.HomeFragment;
import com.android.ifarm.ifarming.ui.fragment.SarsFragment;
import com.android.ifarm.ifarming.ui.fragment.UserFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.tab_bar)
    RadioGroup tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView(this);
        setCurrentFragmentIndex(0);
        tab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        int index = 0;
        if (id == R.id.radio_home) {
            index = 0;
        } else if (id == R.id.radio_basic_collect) {
            index = 1;
        } else if (id == R.id.radio_sars_collect) {
            index = 2;
        } else if (id == R.id.radio_user) {
            index = 3;
        }
        setCurrentFragmentIndex(index);
    }

    private static final Class<?>[] FRAGMENT_CLASSES = {HomeFragment.class,
            BasicFragment.class, SarsFragment.class,
            UserFragment.class};
    Fragment[] mFragments = new Fragment[FRAGMENT_CLASSES.length];

    public void setCurrentFragmentIndex(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : mFragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        if (mFragments[index] == null) {
            mFragments[index] = getFragment(index);
            ft.add(R.id.content, mFragments[index], FRAGMENT_CLASSES[index].getName());
        } else {
            ft.show(mFragments[index]);
        }
        ft.commitAllowingStateLoss();
    }

    private Fragment getFragment(int index) {
        try {
            mFragments[index] = (Fragment) FRAGMENT_CLASSES[index].newInstance();
            return mFragments[index];
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mFragments[0] == null && fragment instanceof HomeFragment) {
            mFragments[0] = fragment;
        } else if (mFragments[1] == null && fragment instanceof BasicFragment) {
            mFragments[1] = fragment;
        } else if (mFragments[2] == null && fragment instanceof SarsFragment) {
            mFragments[2] = fragment;
        } else if (mFragments[3] == null && fragment instanceof UserFragment) {
            mFragments[3] = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }
}
