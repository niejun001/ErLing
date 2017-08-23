package com.yunwan.erling.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gc on 16/10/20.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> listFragment; // Fragment列表
    private List<String> listTitle; // Tab名的列表

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragment, List<String> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }


    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    // 此方法用来显示Tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position % listTitle.size());
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
