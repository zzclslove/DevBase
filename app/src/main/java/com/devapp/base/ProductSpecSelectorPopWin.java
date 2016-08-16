package com.devapp.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.model.Product;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ProductSpecSelectorPopWin extends PopupWindow {

    private View view;
    private Token token;
    private SimpleDraweeView productThumb;
    private TextView productName;

    public ProductSpecSelectorPopWin(Context mContext, Product product) {

        token = (Token) mContext.getApplicationContext();
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_window_spec_selector, null);

        productThumb = (SimpleDraweeView) view.findViewById(R.id.pop_product_image);
        productName = (TextView) view.findViewById(R.id.pop_product_name);

        Uri uri = Uri.parse(token.getRootUrl() + product.getThumb());
        productThumb.setImageURI(uri);
        productName.setText(product.getName());

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_container).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.product_spec_selector_anim);

    }

}
