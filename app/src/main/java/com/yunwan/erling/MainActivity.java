package com.yunwan.erling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Fragment.CategoryFragment;
import com.yunwan.erling.Fragment.HomeFragment;
import com.yunwan.erling.Fragment.MineFragment;
import com.yunwan.erling.Fragment.ShopCartFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.first_image)
    ImageView firstImage;
    @InjectView(R.id.first_text)
    TextView firstText;
    @InjectView(R.id.second_image)
    ImageView secondImage;
    @InjectView(R.id.second_text)
    TextView secondText;
    @InjectView(R.id.third_image)
    ImageView thirdImage;
    @InjectView(R.id.third_text)
    TextView thirdText;
    @InjectView(R.id.four_image)
    ImageView fourImage;
    @InjectView(R.id.four_text)
    TextView fourText;


    private HomeFragment fg1;
    private CategoryFragment fg2;
    private ShopCartFragment fg3;
    private MineFragment fg4;

    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    //未点击颜色
    private int gray = Color.parseColor("#333333");
    //选中颜色
    private int dark = Color.parseColor("#189ce9");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        setChioceItem(0);
    }

    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                firstImage.setImageResource(R.drawable.home11); //需要的话自行修改
                firstText.setTextColor(dark);
//                firstLayout.setBackgroundColor(gray);

                // 如果fg1为空，则创建一个并添加到界面上
                if (fg1 == null) {
                    fg1 = new HomeFragment();
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);
                }

                break;

            case 1:
                secondImage.setImageResource(R.drawable.home22);
                secondText.setTextColor(dark);
//                secondLayout.setBackgroundColor(gray);

                if (fg2 == null) {
                    fg2 = new CategoryFragment();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }

                break;
            case 2:
                thirdImage.setImageResource(R.drawable.home33);
                thirdText.setTextColor(dark);
//                thirdLayout.setBackgroundColor(gray);

                if (fg3 == null) {
                    fg3 = new ShopCartFragment();
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }

                break;

            case 3:
                fourImage.setImageResource(R.drawable.home44);
                fourText.setTextColor(dark);
//                fourLayout.setBackgroundColor(gray);

                if (fg4 == null) {
                    fg4 = new MineFragment();
                    fragmentTransaction.add(R.id.content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }

                break;
        }

        fragmentTransaction.commit();   // 提交

    }

    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        firstImage.setImageResource(R.drawable.home1);
        firstText.setTextColor(gray);
//        firstLayout.setBackgroundColor(whirt);

        secondImage.setImageResource(R.drawable.home2);
        secondText.setTextColor(gray);
//        secondLayout.setBackgroundColor(whirt);
        thirdImage.setImageResource(R.drawable.home3);
        thirdText.setTextColor(gray);
//        thirdLayout.setBackgroundColor(whirt);

        fourImage.setImageResource(R.drawable.home4);
        fourText.setTextColor(gray);
//        fourLayout.setBackgroundColor(whirt);

    }

    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }

        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }

        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
        if (fg4 != null) {
            fragmentTransaction.hide(fg4);
        }
    }

    @OnClick({R.id.first_layout, R.id.second_layout, R.id.third_layout, R.id.four_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_layout:
                setChioceItem(0);
                break;
            case R.id.second_layout:
                setChioceItem(1);
                break;
            case R.id.third_layout:
                setChioceItem(2);
                break;
            case R.id.four_layout:
                setChioceItem(3);
                break;
        }
    }

}
