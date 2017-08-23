package com.yunwan.erling.Moudle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        ImmersionBar.with(this).titleBar(getToolbar())
                .statusBarDarkFont(true, 1)
                .init();
        initView();
        initData();
    }

    //初始布局
    public abstract int getLayoutId();

    //初始化顶部
    public abstract Toolbar getToolbar();

    //初始化控件  使用ButterKnife时，初始化ButterKnife要卸载initView方法中，否则会报空指针
    public abstract void initView();

    //初始化数据
    public abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        ButterKnife.reset(this);
    }

}
