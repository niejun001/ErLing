package com.yunwan.erling.Activity.Mine.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.PictureUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by YUNWAN01 on 2017/8/17.
 */

public class AboutUsActivity extends BaseActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.logo)
    ImageView logo;

    @Override
    public int getLayoutId() {
        return R.layout.about_us;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        PictureUtils.PicassoSmalll(AboutUsActivity.this, "http://mall.yunwan.org/uploads/logo/wap/style1_m.png", logo);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
