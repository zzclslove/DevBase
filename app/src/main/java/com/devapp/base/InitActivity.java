package com.devapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.devapp.R;
import com.devapp.model.Cart;
import com.devapp.model.CartProduct;
import com.devapp.model.InitData;
import com.devapp.model.Product;
import com.devapp.model.ResultData;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitActivity extends Activity {

    private Token token;
    private Activity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        token = (Token)getApplication();
        self = this;

        CustomProgressDialog progressDialog = CustomProgressDialog.createDialog(this);
        progressDialog.setMessage("正在加载中...");
        progressDialog.show();

        token.setRootUrl("http://www.zhouzhiren.name/");

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point autoSize = new Point();
        wm.getDefaultDisplay().getSize(autoSize);
        token.setWindowWidth(autoSize.x);
        token.setWindowHeight(autoSize.y);

        final InitData initData = new InitData();
        token.setInitData(initData);

        Cart cart = new Cart();
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(100);
        cartProduct.setNum(10);
        cartProduct.setDiscount(new BigDecimal("0.78"));
        cartProduct.setPrice(new BigDecimal("100.35"));
        cartProduct.setProductName("澳贝玩具趣味小树 婴幼儿童声趣味小树 婴幼儿童声光积");
        cartProduct.setThumb("http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg");
        cartProduct.setPropsStr("[{propName:'颜色', propValue:'白色'},{propName:'尺寸', propValue:'大号'}]");
        cartProductList.add(cartProduct);
        CartProduct cartProduct1 = new CartProduct();
        cartProduct1.setProductId(101);
        cartProduct1.setNum(10);
        cartProduct1.setDiscount(new BigDecimal("0.78"));
        cartProduct1.setPrice(new BigDecimal("100.35"));
        cartProduct1.setProductName("澳贝玩具趣味小树 婴幼儿童声趣味小树 婴幼儿童声光积");
        cartProduct1.setThumb("http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg");
        cartProduct1.setPropsStr("[{propName:'颜色', propValue:'白色'},{propName:'尺寸', propValue:'大号'}]");
        cartProductList.add(cartProduct1);
        CartProduct cartProduct2 = new CartProduct();
        cartProduct2.setProductId(102);
        cartProduct2.setNum(10);
        cartProduct2.setDiscount(new BigDecimal("0.79"));
        cartProduct2.setPrice(new BigDecimal("100.35"));
        cartProduct2.setProductName("澳贝玩具趣味小树 婴幼儿童声趣味小树 婴幼儿童声光积");
        cartProduct2.setThumb("http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg");
        cartProduct2.setPropsStr("[{propName:'颜色', propValue:'白色'},{propName:'尺寸', propValue:'大号'}]");
        cartProductList.add(cartProduct2);

        cart.setCartProducts(cartProductList);
        token.getInitData().setCart(cart);

        RequestParams paramsCategory = new RequestParams(token.getRootUrl() + "api/index.php");
        paramsCategory.addQueryStringParameter("action", "get_init_data");
        x.http().get(paramsCategory, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultData resultData = gson.fromJson(result, ResultData.class);
                if(resultData.isResult()){
                    InitData initDataRes = gson.fromJson(resultData.getData(), InitData.class);
                    token.getInitData().setCategoryList(initDataRes.getCategoryList());
                    token.getInitData().setRecommendProducts(initDataRes.getRecommendProducts());
                    token.getInitData().setTopicList(initDataRes.getTopicList());
                    token.setProductImageScale(initDataRes.getProductImageWidth()/initDataRes.getProductImageHeight());
                    token.getInitData().setFindingProductList(initDataRes.getFindingProductList());
                    Intent i = new Intent(self, MainActivity.class);
                    startActivity(i);
                    self.finish();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                AlertDialog.Builder builder=new AlertDialog.Builder(self);
                builder.setMessage("应用加载失败，是否重新加载？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        Intent i = new Intent(self, InitActivity.class);
                        startActivity(i);
                        self.finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        self.finish();
                    }
                });
                builder.create().show();
            }
            @Override
            public void onCancelled(CancelledException cex) { }
            @Override
            public void onFinished() { }
        });

    }

}
