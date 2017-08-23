package com.yunwan.erling.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yunwan.erling.Activity.ShopCart.adapter.ShopcartExpandableListViewAdapter;
import com.yunwan.erling.Activity.ShopCart.bean.GroupInfo;
import com.yunwan.erling.Activity.ShopCart.bean.ProductInfo;
import com.yunwan.erling.Activity.ShopCart.widget.SuperExpandableListView;
import com.yunwan.erling.R;
import com.yunwan.erling.Tools.DividerGridItemDecoration;
import com.yunwan.erling.Tools.ToastUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by YUNWAN01 on 2017/8/14.
 */

public class ShopCartFragment extends Fragment
        implements ShopcartExpandableListViewAdapter.CheckInterface,
        View.OnClickListener,
        ShopcartExpandableListViewAdapter.ModifyCountInterface {

    @InjectView(R.id.exListView)
    SuperExpandableListView exListView;
    @InjectView(R.id.all_chekbox)
    CheckBox cb_check_all;
    @InjectView(R.id.tv_total_price)
    TextView tv_total_price;
    @InjectView(R.id.tv_delete)
    TextView tv_delete;
    @InjectView(R.id.tv_go_to_pay)
    TextView tv_go_to_pay;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private Context context;
    private View bottomView;
    private RecyclerView recycler;

    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    private ShopcartExpandableListViewAdapter selva;
    private List<GroupInfo> groups = new ArrayList<GroupInfo>();// 组元素数据列表
    private Map<String, List<ProductInfo>> children = new HashMap<>();// 子元素数据列表

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        ButterKnife.inject(this, view);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
        initView();
        initEvents();
        commodity();
        return view;
    }

    private void initView() {
        context = getActivity();
        virtualData();
    }

    private void initEvents() {
        selva = new ShopcartExpandableListViewAdapter(groups, children, getActivity(), deleat);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        cb_check_all.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_go_to_pay.setOnClickListener(this);
        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                ToastUtil.show(getActivity(), children.get(groups.get(groupPosition).getId()).get(childPosition).getDesc());
                return true;
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ImmersionBar.with(this).titleBar(toolbar)
                .statusBarDarkFont(true, 1)
                .init();
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void virtualData() {

        for (int i = 0; i < 50; i++) {
            groups.add(new GroupInfo(i + "", "贰零农场" + (i + 1) + "号店"));
            List<ProductInfo> products = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                products.add(new ProductInfo(j + "", "商品", "", groups.get(i).getName() + "的第" + (j + 1) + "个商品", 120.00 + i * j, 1 + j));
            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;

        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    //删除商品
    ShopcartExpandableListViewAdapter.Deleat deleat = new ShopcartExpandableListViewAdapter.Deleat() {
        @Override
        public void deleat(int groupPosition, int childPosition, boolean isChecked) {
            List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());
            childs.remove(childPosition);
            if (childs.size() == 0) {//child没有了，group也就没有了
                groups.remove(groupPosition);
            }
            selva.notifyDataSetChanged();
            calculate();
        }
    };

    @Override
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.show();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDelete();
                    }
                });
                alert.show();
                break;
        }
    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    public void doDelete() {
        List<GroupInfo> toBeDeleteGroups = new ArrayList<GroupInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            if (group.isChoosed()) {

                toBeDeleteGroups.add(group);
            }
            List<ProductInfo> toBeDeleteProducts = new ArrayList<ProductInfo>();// 待删除的子元素列表
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);

        }

        groups.removeAll(toBeDeleteGroups);

        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(cb_check_all.isChecked());
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(cb_check_all.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                ProductInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }
        }
        tv_total_price.setText("￥" + totalPrice);
        tv_go_to_pay.setText("去支付(" + totalCount + ")");
    }

    private boolean isAllCheck() {
        for (GroupInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //底部推荐商品
    public void commodity() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(i + "");
        }
        bottomView = LayoutInflater.from(getActivity()).inflate(R.layout.shop_cart_head, null);
        recycler = bottomView.findViewById(R.id.recycler);
        exListView.addFooterView(bottomView);

        recycler.addItemDecoration(new DividerGridItemDecoration(getActivity(), R.color.bgColor, 5));
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.commodity_info_bottom_item, data) {
            @Override
            protected void convert(ViewHolder holder, final String dataBean, final int position) {

            }
        });
    }
}
