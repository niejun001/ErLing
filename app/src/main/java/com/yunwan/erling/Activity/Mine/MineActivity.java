package com.yunwan.erling.Activity.Mine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.ActionSheetDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Activity.Mine.change.ChangeNameActivity;
import com.yunwan.erling.Activity.Mine.change.ChangeSignActivity;
import com.yunwan.erling.Activity.Mine.myaddress.MyAddressActivity;
import com.yunwan.erling.Moudle.BaseActivity;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.LQRPhotoSelectUtils;
import com.yunwan.erling.Tools.ToastUtil;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by YUNWAN01 on 2017/8/14.
 */

public class MineActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.title)
    TextView title;

    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void initView() {
        title.setText("个人信息");
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
//                mTvPath.setText(outputFile.getAbsolutePath());
//                mTvUri.setText(outputUri.toString());
            }
        }, true);//true裁剪，false不裁剪
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.rl_head, R.id.rl_name, R.id.rl_sex, R.id.rl_city, R.id.rl_sign, R.id.rl_code, R.id.rl_my_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                new ActionSheetDialog(MineActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Photograph();
                                    }
                                })
                        .addSheetItem("相册选择", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        ChoosePhoto();
                                    }
                                }).show();
                break;
            case R.id.rl_name:
                startActivity(new Intent(MineActivity.this, ChangeNameActivity.class));
                break;
            case R.id.rl_sex:
                new ActionSheetDialog(MineActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("男", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        ToastUtil.show(MineActivity.this, "男");
                                    }
                                })
                        .addSheetItem("女", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        ToastUtil.show(MineActivity.this, "女");
                                    }
                                })
                        .addSheetItem("保密", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        ToastUtil.show(MineActivity.this, "保密");
                                    }
                                }).show();
                break;
            case R.id.rl_city:
                break;
            case R.id.rl_sign:
                startActivity(new Intent(MineActivity.this, ChangeSignActivity.class));
                break;
            case R.id.rl_code:
                break;
            case R.id.rl_my_add:
                startActivity(new Intent(MineActivity.this, MyAddressActivity.class));
                break;
        }
    }

    //拍照
    private void Photograph() {
        PermissionGen.with(MineActivity.this)
                .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ).request();
    }

    //选择相册
    private void ChoosePhoto() {
        PermissionGen.needPermission(MineActivity.this,
                LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        showDialog();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-贰零农场-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + MineActivity.this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //使用构建器创建出对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }
}
