package com.yunwan.erling.Activity.Mine.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yunwan.erling.Moudle.BaseFragment;
import com.yunwan.erling.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YUNWAN01 on 2017/8/15.
 */

public class Order1 extends BaseFragment {
    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private Adapter adapter;
    private List<String> data = new ArrayList<>();
    private List<String> data1 = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView(View view) {
        ButterKnife.inject(this, view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "");
        }
        for (int i = 0; i < 3; i++) {
            data1.add(i + "");
        }
        adapter.setNewData(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public Adapter() {
            super(R.layout.order1_item);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            RecyclerView recycler_comm = (RecyclerView) helper.getView(R.id.recycler_comm);
            recycler_comm.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recycler_comm.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.order_comm_item, data1) {
                @Override
                protected void convert(ViewHolder holder, final String dataBean, final int position) {

                }
            });
        }
    }
}
