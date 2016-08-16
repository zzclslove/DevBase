package com.devapp.base;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.model.Product;
import com.devapp.util.DensityUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ProductSpecSelectorPopWin extends PopupWindow {

    private View view;
    private Token token;
    private SimpleDraweeView productThumb;
    private TextView productName;
    private LinearLayout productTitle;
    private TextView productPrice;
    private TextView productPriceDecimal;
    private TextView productOrgPrice;

    public ProductSpecSelectorPopWin(Context mContext, Product product) {

        token = (Token) mContext.getApplicationContext();
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_window_spec_selector, null);

        productTitle = (LinearLayout) view.findViewById(R.id.pop_product_title);
        productThumb = (SimpleDraweeView) view.findViewById(R.id.pop_product_image);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        productPriceDecimal = (TextView) view.findViewById(R.id.product_price_d);
        productOrgPrice = (TextView) view.findViewById(R.id.product_org_price);

        BigDecimal price = new BigDecimal(product.getShop_price());
        BigDecimal orgPrice = new BigDecimal("0");
        if(product.getPromote_price().length() > 0){
            orgPrice = new BigDecimal(product.getShop_price());
            price = new BigDecimal(product.getPromote_price());
        }

        int intPrice = price.setScale(2).intValue();
        int decimalPrice = Integer.parseInt(new java.text.DecimalFormat("0").format(((price.setScale(2).doubleValue() - intPrice) * 100)));
        if(decimalPrice != 0){
            productPrice.setText(intPrice + ".");
            productPriceDecimal.setText(decimalPrice + "");
        }else{
            productPrice.setText(intPrice + ".");
            productPriceDecimal.setText("00");
        }

        if(orgPrice.compareTo(BigDecimal.ZERO) != 0){
            productOrgPrice.setText("￥" + orgPrice + "");
            productOrgPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        }

        int imgHeight = (int) (DensityUtil.dip2px(token, 100) / token.getProductImageScale());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(token, 100), imgHeight);
        layoutParams.leftMargin = DensityUtil.dip2px(token, 10);
        productThumb.setLayoutParams(layoutParams);
        int infoHeight = imgHeight - DensityUtil.dip2px(token, 12);
        RelativeLayout.LayoutParams layoutParamsTitle = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, infoHeight);
        layoutParamsTitle.topMargin = DensityUtil.dip2px(token, 12);
        productTitle.setLayoutParams(layoutParamsTitle);
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
        //ColorDrawable dw = new ColorDrawable(0x90000000);
        // 设置弹出窗体的背景
        //this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.product_spec_selector_anim);

    }

}
