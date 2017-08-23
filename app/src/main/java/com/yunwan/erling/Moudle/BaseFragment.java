package com.yunwan.erling.Moudle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by YUNWAN01 on 2017/8/15.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this, view);
        initView(view);
        initData();
        return view;
    }

    //初始布局
    public abstract int getLayoutId();

    //初始化控件  使用ButterKnife时，初始化ButterKnife要卸载initView方法中，否则会报空指针
    public abstract void initView(View view);

    //初始化数据
    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
