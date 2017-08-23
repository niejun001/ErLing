package com.yunwan.erling.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Activity.Mine.MineActivity;
import com.yunwan.erling.Activity.Mine.MyCollectionActivity;
import com.yunwan.erling.Activity.Mine.MyPageActivity;
import com.yunwan.erling.Activity.Mine.myaddress.MyAddressActivity;
import com.yunwan.erling.Activity.Mine.order.OrderActivity;
import com.yunwan.erling.Activity.Mine.setting.SettingActivity;
import com.yunwan.erling.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/14.
 */

public class MineFragment extends Fragment {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.inject(this, view);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
    }

    @OnClick({R.id.rl_info, R.id.order_all, R.id.order1, R.id.order2, R.id.order3, R.id.order4,
            R.id.order5, R.id.rl_setting, R.id.rl_collection, R.id.my_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_info:
                startActivity(new Intent(getActivity(), MineActivity.class));
                break;
            case R.id.order_all:
                Intent all = new Intent(getActivity(), OrderActivity.class);
                all.putExtra("who", 0);
                startActivity(all);
                break;
            case R.id.order1:
                Intent order1 = new Intent(getActivity(), OrderActivity.class);
                order1.putExtra("who", 1);
                startActivity(order1);
                break;
            case R.id.order2:
                Intent order2 = new Intent(getActivity(), OrderActivity.class);
                order2.putExtra("who", 2);
                startActivity(order2);
                break;
            case R.id.order3:
                Intent order3 = new Intent(getActivity(), OrderActivity.class);
                order3.putExtra("who", 3);
                startActivity(order3);
                break;
            case R.id.order4:
                Intent order4 = new Intent(getActivity(), OrderActivity.class);
                order4.putExtra("who", 4);
                startActivity(order4);
                break;
            case R.id.order5:
                break;
            case R.id.rl_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_collection:
                startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                break;
            case R.id.my_page:
                startActivity(new Intent(getActivity(), MyPageActivity.class));
                break;
        }
    }
}
