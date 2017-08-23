package com.yunwan.erling.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Activity.Category.GoodListActivity;
import com.yunwan.erling.Moudle.BaseFragment;
import com.yunwan.erling.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YUNWAN01 on 2017/8/14.
 */

public class CategoryFragment extends BaseFragment {
    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @InjectView(R.id.recycler_right)
    RecyclerView recyclerRight;


    private LeftAdapter leftAdapter;
    private RightAdapter rigthAdapter;

    private View rightHeadView;
    private View rightFootView;

    @Override
    public int getLayoutId() {
        return R.layout.category_fragment;
    }

    @Override
    public void initView(View view) {
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerLeft.setLayoutManager(linearLayoutManager);
        leftAdapter = new LeftAdapter();
        recyclerLeft.setAdapter(leftAdapter);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerRight.setLayoutManager(gridLayoutManager);
        rigthAdapter = new RightAdapter();
        recyclerRight.setAdapter(rigthAdapter);
        addRightHeadView();
        addRightFootView();
        click();
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
    }

    private void addRightHeadView() {
        rightHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.category_item_tight_top,
                (ViewGroup) recyclerRight.getParent(), false);
        rigthAdapter.addHeaderView(rightHeadView);
    }

    private void addRightFootView() {
        rightFootView = LayoutInflater.from(getActivity()).inflate(R.layout.category_item_tight_bottom,
                (ViewGroup) recyclerRight.getParent(), false);
        rigthAdapter.addFooterView(rightFootView);
    }

    @Override
    public void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        leftAdapter.setNewData(data);
        rigthAdapter.setNewData(data);
    }

    public void click() {
        rigthAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), GoodListActivity.class));
            }
        });
    }

    class LeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public LeftAdapter() {
            super(R.layout.catrgory_item_left);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    class RightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public RightAdapter() {
            super(R.layout.category_item_right);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
