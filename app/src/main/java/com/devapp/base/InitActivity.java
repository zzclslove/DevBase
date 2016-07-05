package com.devapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.devapp.R;
import com.devapp.model.Cart;
import com.devapp.model.CartProduct;
import com.devapp.model.Category;
import com.devapp.model.Product;
import com.devapp.model.ResultData;
import com.devapp.model.Topic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
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

        token.setRootUrl("http://zhouzhiren.name/");

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point autoSize = new Point();
        wm.getDefaultDisplay().getSize(autoSize);
        token.setWindowWidth(autoSize.x);
        token.setWindowHeight(autoSize.y);

        token.setProductImageScale(1);  //设置产品图片宽高比

        RequestParams paramsCategory = new RequestParams(token.getRootUrl() + "api/category.php");
        paramsCategory.addQueryStringParameter("action", "get_all_category");
        x.http().get(paramsCategory, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultData resultData = gson.fromJson(result, ResultData.class);
                if(resultData.isResult()){
                    Type type = new TypeToken<ArrayList<Category>>(){}.getType();
                    List<Category> categoryList = gson.fromJson(resultData.getData(), type);
                    token.setCategoryList(categoryList);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) { }
            @Override
            public void onCancelled(CancelledException cex) { }
            @Override
            public void onFinished() { }
        });

        RequestParams paramsTopic = new RequestParams(token.getRootUrl() + "api/index.php");
        paramsTopic.addQueryStringParameter("action", "get_topic_list");
        x.http().get(paramsTopic, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultData resultData = gson.fromJson(result, ResultData.class);
                if(resultData.isResult()){
                    Type type = new TypeToken<ArrayList<Topic>>(){}.getType();
                    List<Topic> topicList = gson.fromJson(resultData.getData(), type);
                    token.setTopicList(topicList);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) { }
            @Override
            public void onCancelled(CancelledException cex) { }
            @Override
            public void onFinished() { }
        });

        Map findingProductList = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("245.88"), new BigDecimal("0.89"), 100,  12, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("125.00"), new BigDecimal("0.89"), 100, 15, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("960.12"), new BigDecimal("0.89"), 100, 300, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("1250.35"), new BigDecimal("0.89"), 100, 1200, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("330.45"), new BigDecimal("0.89"), 100, 20, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("124.00"), new BigDecimal("0.89"), 100, 12, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("136.88"), new BigDecimal("0.89"), 100, 10, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        productList.add(new Product("澳贝玩具趣味小树 婴幼儿童声光积", "买就送一袋捏碎面，任性就是这样。", new BigDecimal("875.12"), new BigDecimal("0.89"), 100, 12, "http://img14.360buyimg.com/n1/jfs/t2470/269/1812220721/155606/6a768005/567cfb74N1d5efeb7.jpg"));
        findingProductList.put("productList", productList);
        findingProductList.put("endTime", "2016-06-18 08:14:47");
        token.setFindingProductList(findingProductList);

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
        token.setCart(cart);

        Intent i = new Intent(self, MainActivity.class);
        startActivity(i);
        self.finish();
    }

}
