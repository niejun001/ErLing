package com.yunwan.erling.Activity.Mine.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Activity.Mine.order.fragment.Order1;
import com.yunwan.erling.Activity.Mine.order.fragment.Order2;
import com.yunwan.erling.Activity.Mine.order.fragment.Order3;
import com.yunwan.erling.Activity.Mine.order.fragment.Order4;
import com.yunwan.erling.Activity.Mine.order.fragment.Order5;
import com.yunwan.erling.Adapter.MyFragmentPagerAdapter;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/15.
 */

public class OrderActivity extends BaseActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<Fragment> listFragment;
    private List<String> listTitle;

    private Order1 order1;
    private Order2 order2;
    private Order3 order3;
    private Order4 order4;
    private Order5 order5;

    private int who = 0;

    @Override
    public int getLayoutId() {
        return R.layout.order;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
        title.setText("订单");

        who = getIntent().getIntExtra("who",0);

        order1 = new Order1();
        order2 = new Order2();
        order3 = new Order3();
        order4 = new Order4();
        order5 = new Order5();

        listFragment = new ArrayList<>();
        listFragment.add(order1);
        listFragment.add(order2);
        listFragment.add(order3);
        listFragment.add(order4);
        listFragment.add(order5);

        listTitle = new ArrayList<>();
        listTitle.add("全部");
        listTitle.add("待付款");
        listTitle.add("待发货");
        listTitle.add("待收货");
        listTitle.add("待评价");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(4)));

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listFragment, listTitle);

        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(who);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
