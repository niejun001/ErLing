package com.yunwan.erling.Activity.Mine.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/16.
 */

public class SettingActivity extends BaseActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.setting;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        title.setText("设置");
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                startActivity(new Intent(SettingActivity.this, SetInfoActivity.class));
                break;
            case R.id.rl2:
                startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));
                break;
            case R.id.rl3:
                break;
            case R.id.rl4:
                new AlertDialog(SettingActivity.this).builder().setTitle("提示")
                        .setMsg("是否退出当前账号？")
                        .setPositiveButton("确认退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
        }
    }
}
