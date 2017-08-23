package com.yunwan.erling.Activity.Mine.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/17.
 */

public class SetInfoActivity extends BaseActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.set_info;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        title.setText("账号与安全");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.back, R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl1:
                break;
            case R.id.rl2:
                break;
            case R.id.rl3:
                break;
            case R.id.rl4:
                break;
            case R.id.rl5:
                break;
        }
    }
}
