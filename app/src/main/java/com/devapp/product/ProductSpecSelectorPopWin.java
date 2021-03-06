package com.devapp.product;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.SpecWarpLinearLayout;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.model.ProductImage;
import com.devapp.model.ProductSpec;
import com.devapp.model.ProductSpecValue;
import com.devapp.user.LoginActivity;
import com.devapp.util.DensityUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ProductSpecSelectorPopWin extends PopupWindow {

    private View view;
    private Context context;
    private Token token;
    private SimpleDraweeView productThumb;
    private TextView productName;
    private LinearLayout productTitle;
    private TextView productPrice;
    private TextView productPriceDecimal;
    private TextView productOrgPrice;
    private TextView productTitleDescription;
    private LinearLayout productSpecContainer;
    private RelativeLayout popupWindowContainer;
    private ImageButton addButton;
    private ImageButton reduceButon;
    private TextView editNum;
    private List<Integer> specIdSelected;
    private TextView addToCart;

    public ProductSpecSelectorPopWin(Context mContext, final Product product) {
        this.context = mContext;
        token = (Token) mContext.getApplicationContext();
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_window_spec_selector, null);

        productTitle = (LinearLayout) view.findViewById(R.id.pop_product_title);
        productThumb = (SimpleDraweeView) view.findViewById(R.id.pop_product_image);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        productPriceDecimal = (TextView) view.findViewById(R.id.product_price_d);
        productOrgPrice = (TextView) view.findViewById(R.id.product_org_price);
        productTitleDescription = (TextView) view.findViewById(R.id.product_title_desc);
        productSpecContainer = (LinearLayout) view.findViewById(R.id.product_spec_container);
        popupWindowContainer = (RelativeLayout) view.findViewById(R.id.pop_container);
        addButton = (ImageButton) view.findViewById(R.id.cart_item_add);
        reduceButon = (ImageButton) view.findViewById(R.id.cart_item_reduce);
        editNum = (TextView) view.findViewById(R.id.cart_item_num);
        addToCart = (TextView) view.findViewById(R.id.btn_addtocart);
        specIdSelected = new ArrayList<>();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!token.isLogined()){
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                    dismiss();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int buyQty = Integer.parseInt(editNum.getText().toString());
                editNum.setText((buyQty + 1) + "");
            }
        });
        reduceButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int buyQty = Integer.parseInt(editNum.getText().toString());
                if(buyQty > 1){
                    editNum.setText((buyQty - 1) + "");
                }
            }
        });
        editNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductBuyNumEditDialog.Builder builder = new ProductBuyNumEditDialog.Builder(context, Integer.parseInt(editNum.getText().toString()), editNum);
                builder.setPositiveButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        popupWindowContainer.setFocusable(true);
        popupWindowContainer.setFocusableInTouchMode(true);
        popupWindowContainer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK){
                    dismiss();
                }
                return false;
            }
        });

        for(ProductSpec productSpec: product.getSpecs()){
            TextView productSpecTitle = new TextView(context);
            productSpecTitle.setTextColor(ContextCompat.getColor(context, R.color.black_3c));
            productSpecTitle.setTextSize(16.5f);
            productSpecTitle.setPadding(0, DensityUtil.dip2px(token, 15), 0, DensityUtil.dip2px(token, 15));
            productSpecTitle.setText(productSpec.getName());
            productSpecContainer.addView(productSpecTitle);
            final SpecWarpLinearLayout specWarpLinearLayout = new SpecWarpLinearLayout(context);
            specWarpLinearLayout.setGrivate(SpecWarpLinearLayout.Gravite.LEFT);
            specWarpLinearLayout.setHorizontal_Space(DensityUtil.dip2px(token, 10));
            specWarpLinearLayout.setVertical_Space(DensityUtil.dip2px(token, 10));
            specWarpLinearLayout.setPadding(0, 0, 0, DensityUtil.dip2px(token, 15));
            for(ProductSpecValue productSpecValue: productSpec.getValues()){
                final TextView productSpecValueName = new TextView(context);
                productSpecValueName.setText(productSpecValue.getLabel());
                productSpecValueName.setPadding(DensityUtil.dip2px(token, 12), DensityUtil.dip2px(token, 6), DensityUtil.dip2px(token, 12), DensityUtil.dip2px(token, 6));
                productSpecValueName.setBackgroundResource(R.drawable.corners_noborder_bg);
                productSpecValueName.setTextSize(12);
                productSpecValueName.setTextColor(ContextCompat.getColor(context, R.color.black_3c));
                productSpecValueName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int childCount = specWarpLinearLayout.getChildCount();
                        for(int i = 0; i < childCount; i++){
                            TextView productSpecTv = (TextView) specWarpLinearLayout.getChildAt(i);
                            productSpecTv.setBackgroundResource(R.drawable.corners_noborder_bg);
                            productSpecTv.setTextColor(ContextCompat.getColor(context, R.color.black_3c));
                        }
                        productSpecValueName.setBackgroundResource(R.drawable.corners_noborder_bg_activity);
                        productSpecValueName.setTextColor(ContextCompat.getColor(context, R.color.white_ff));
                        for(ProductImage productImage: product.getGoods_img()){
                            if(productSpecValueName.getText().toString().equals(productImage.getTitle())){
                                Uri uri = Uri.parse(token.getRootUrl() + productImage.getUrl());
                                productThumb.setImageURI(uri);
                            }
                        }
                    }
                });
                specWarpLinearLayout.addView(productSpecValueName);
            }
            productSpecContainer.addView(specWarpLinearLayout);
            View partBorder = new View(context);
            partBorder.setBackgroundColor(ContextCompat.getColor(context, R.color.black_border));
            LinearLayout.LayoutParams partBorderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(token, 0.5f));
            partBorder.setLayoutParams(partBorderParams);
            productSpecContainer.addView(partBorder);
        }

        String specSelectDesc = "请选择 ";
        int index = 0;
        for(ProductSpec productSpec: product.getSpecs()){
            index ++;
            specSelectDesc += productSpec.getName();
            if(index != product.getSpecs().size()){
                specSelectDesc += "，";
            }
        }
        productTitleDescription.setText(specSelectDesc);

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

        int imgWidth = (int) (DensityUtil.dip2px(token, 120) * token.getProductImageScale());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imgWidth, DensityUtil.dip2px(token, 120));
        layoutParams.leftMargin = DensityUtil.dip2px(token, 10);
        productThumb.setLayoutParams(layoutParams);
        productTitle.setPadding(DensityUtil.dip2px(token, 20) + imgWidth, DensityUtil.dip2px(token, 5), DensityUtil.dip2px(token, 10), DensityUtil.dip2px(token, 5));
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
