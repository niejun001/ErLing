package com.yunwan.erling.Activity.Mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/18.
 */

public class MyPageActivity extends BaseActivity {
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    ImmersionBar immersionBar;

    private Adapter adapter;
    private View headView;
    private LinearLayout ll;
    private RelativeLayout rl;
    private int bannerHeight = 200;

    @Override
    public int getLayoutId() {
        return R.layout.my_page;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        immersionBar = ImmersionBar.with(MyPageActivity.this)
                .statusBarDarkFont(true, 1)
                .addViewSupportTransformColor(toolbar, R.color.white);
        immersionBar
                .statusBarDarkFont(true, 1)
                .statusBarAlpha(0).init();

        title.setText("我的钱包");
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(MyPageActivity.this, 2);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addItemDecoration(new DividerGridItemDecoration(MyPageActivity.this, R.color.bgColor, 1));
        adapter = new Adapter();
        recycler.setAdapter(adapter);
        addHeadView();
        setListener();
    }

    @Override
    public void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i + "");
        }
        adapter.setNewData(data);
    }

    private void addHeadView() {
        headView = LayoutInflater.from(MyPageActivity.this).inflate(R.layout.my_page_head, (ViewGroup) recycler.getParent(), false);
        ll = headView.findViewById(R.id.ll);
        rl = headView.findViewById(R.id.rl);
        adapter.addHeaderView(headView);
        ll.getBackground().setAlpha(150);
    }

    private void setListener() {

        ViewGroup.LayoutParams bannerParams = rl.getLayoutParams();
        ViewGroup.LayoutParams titleBarParams = toolbar.getLayoutParams();
        bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(MyPageActivity.this);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    title.setVisibility(View.GONE);
                    immersionBar.statusBarAlpha(alpha)
                            .init();
                } else {
                    title.setVisibility(View.VISIBLE);
                    immersionBar.statusBarAlpha(1.0f)
                            .init();
                }
            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public Adapter() {
            super(R.layout.commodity_info_head_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
