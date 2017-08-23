package com.yunwan.erling.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yunwan.erling.Activity.Home.commodity.CommodityInfoActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.GlideImageLoader;
import com.yunwan.erling.Tools.PictureUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YUNWAN01 on 2017/8/14.
 */

public class HomeFragment extends Fragment {
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ll_toolbar)
    LinearLayout llToolbar;
    @InjectView(R.id.logo)
    ImageView logo;

    private Adapter adapter;
    private View headView;
    private View bottomView;
    private Banner banner;
    List<String> data = new ArrayList<>();
    private List<String> images = new ArrayList<>();//banner图
    private int bannerHeight = 200;
    private boolean i = false;//判断状态栏字体的颜色

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.inject(this, view);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(i, 1)
                .init();
        initView();
        setListener();
        return view;
    }

    private void initView() {
        PictureUtils.PicassoSmalll(getActivity(), "http://mall.yunwan.org/uploads/logo/wap/style1_m.png", logo);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(adapter);
        initData();
        addHeadView();
        addBottomView();
        click();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "");
        }
        adapter.setNewData(data);
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c880155d9a.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c88065cd52.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c880f44e6b.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c8815f2cbf.jpg");
        images.add("http://zouzoua.com/uploads/ads/1707/05/14/595c881b05a54.jpg");

    }

    private List<String> getData() {
        List<String> d = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            d.add(i + "");
        }
        data.addAll(d);
        return data;
    }

    private void click() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), CommodityInfoActivity.class));
            }
        });
    }

    private void addHeadView() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_head, (ViewGroup) recycler.getParent(), false);
        banner = (Banner) headView.findViewById(R.id.banner);
        adapter.addHeaderView(headView);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .start();
    }

    private void addBottomView() {
        bottomView = LayoutInflater.from(getActivity()).inflate(R.layout.home_bottom, (ViewGroup) recycler.getParent(), false);
        adapter.addFooterView(bottomView);
    }

    private void setListener() {
        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.size() <= 10) {
                            adapter.addData(getData());
                            adapter.loadMoreComplete();
                        } else {
                            adapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, recycler);

        ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
        ViewGroup.LayoutParams titleBarParams = toolbar.getLayoutParams();
        bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    llToolbar.setVisibility(View.GONE);
                    logo.setVisibility(View.GONE);
                    i = false;
                    ImmersionBar.with(getActivity())
                            .statusBarDarkFont(i, 1)
                            .init();

                } else {
                    llToolbar.setVisibility(View.VISIBLE);
                    logo.setVisibility(View.VISIBLE);
                    i = true;
                    ImmersionBar.with(getActivity())
                            .statusBarDarkFont(i, 1)
                            .init();
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(i, 1)
                .init();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public Adapter() {
            super(R.layout.home_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

}
