package com.yunwan.erling.Activity.Mine.myaddress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
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

public class MyAddressActivity extends BaseActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private List<String> data = new ArrayList<>();
    private Adapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.my_address;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        title.setText("收货地址");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAddressActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
        adapter.setNewData(data);
        click();
    }

    @Override
    public void initData() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "");
        }
        adapter.setNewData(data);
    }

    @OnClick({R.id.back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                startActivity(new Intent(MyAddressActivity.this, AddAddressActivity.class));
                break;
        }
    }

    public void click() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.change_add:
                        startActivity(new Intent(MyAddressActivity.this, ChangeAddressActivity.class));
                        break;
                }
            }
        });
    }

    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public Adapter() {
            super(R.layout.my_address_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.addOnClickListener(R.id.change_add);
        }
    }
}
