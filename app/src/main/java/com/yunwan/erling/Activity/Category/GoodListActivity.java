package com.yunwan.erling.Activity.Category;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yunwan.erling.Activity.Home.commodity.CommodityInfoActivity;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/17.
 */

public class GoodListActivity extends BaseActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private Adapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.good_list;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        title.setText("商品列表");

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(GoodListActivity.this, 2);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addItemDecoration(new DividerGridItemDecoration(GoodListActivity.this, R.color.bgColor, 5));
        adapter = new Adapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        adapter.setNewData(data);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public Adapter() {
            super(R.layout.commodity_info_bottom_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
