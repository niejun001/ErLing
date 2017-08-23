package com.yunwan.erling.Activity.Home.commodity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yunwan.erling.Fragment.HomeFragment;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.DividerGridItemDecoration;
import com.yunwan.erling.Tools.GlideImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/15.
 */

public class CommodityInfoActivity extends BaseActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private Adapter adapter;
    private HeadAdapter headAdapter;
    private View headView;
    private Banner banner;
    private View bottomView;
    private RecyclerView recyclerHead, recyclerBottom;
    List<String> data = new ArrayList<>();
    private List<String> images = new ArrayList<>();//banner图

    @Override
    public int getLayoutId() {
        return R.layout.commodity_info;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        title.setText("商品");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommodityInfoActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
//        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(adapter);
        addHeadView();
        addBottomView();
    }

    @Override
    public void initData() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "");
        }
        adapter.setNewData(data);
    }

    //头部布局
    private void addHeadView() {
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c880155d9a.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c88065cd52.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c880f44e6b.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c8815f2cbf.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c881b05a54.jpg");
        headView = LayoutInflater.from(CommodityInfoActivity.this).inflate(R.layout.commodity_info_head, (ViewGroup) recycler.getParent(), false);
        banner = (Banner) headView.findViewById(R.id.banner);
        recyclerHead = (RecyclerView) headView.findViewById(R.id.recycler_head);
        adapter.addHeaderView(headView);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .start();
        addRecyclerHead();
    }

    //头部评价
    private void addRecyclerHead() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommodityInfoActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerHead.setLayoutManager(linearLayoutManager);
        headAdapter = new HeadAdapter();
        recyclerHead.setAdapter(headAdapter);
        headAdapter.setNewData(data);
    }

    //底部布局
    private void addBottomView() {
        bottomView = LayoutInflater.from(CommodityInfoActivity.this).inflate(R.layout.commodity_info_bottom, (ViewGroup) recycler.getParent(), false);
        recyclerBottom = (RecyclerView) bottomView.findViewById(R.id.recycler_bottom);
        adapter.addFooterView(bottomView);
        addBottomRecycler();
    }

    //添加底部的推荐商品
    private void addBottomRecycler() {
        recyclerBottom.addItemDecoration(new DividerGridItemDecoration(CommodityInfoActivity.this, R.color.bgColor, 5));
        recyclerBottom.setLayoutManager(new GridLayoutManager(CommodityInfoActivity.this, 2));
        recyclerBottom.setAdapter(new CommonAdapter<String>(CommodityInfoActivity.this, R.layout.commodity_info_bottom_item, data) {
            @Override
            protected void convert(ViewHolder holder, final String dataBean, final int position) {

            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        //在页面可见时开始轮播，
        //默认的是页面初始化时就开始轮播了，如果你不需要可以再onCreate方法里设置banner.isAutoPlay(false);
        banner.isAutoPlay(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        //在页面不可见时停止轮播
        banner.isAutoPlay(false);
    }

    //整个页面适配器
    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public Adapter() {
            super(R.layout.commodity_info_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    //头部评价适配器
    class HeadAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public HeadAdapter() {
            super(R.layout.commodity_info_head_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
