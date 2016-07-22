package com.devapp.task;

import com.devapp.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shizhefei.task.Data;
import com.shizhefei.task.ProgressSender;
import com.shizhefei.task.Task;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/21.
 */
public class GetProductDataTask implements Task<Product, String> {

    private int productId;
    private String url;

    public GetProductDataTask(String url, int productId) {
        super();
        this.productId = productId;
        this.url = url;
    }

    @Override
    public Data<Product, String> execute(ProgressSender progressSender) throws Exception {
        Request request = new Request.Builder().url(url + "&id=" + productId).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        Gson gson = new Gson();
        String productJson = response.body().string();
        Product resultData = new Product();
        try {
            resultData = gson.fromJson(productJson, Product.class);
        }catch (JsonSyntaxException e){
            return Data.madeFail(e.toString());
        }
        if(resultData != null){
            return Data.madeSuccess(resultData);
        }else{
            return Data.madeFail("产品加载失败。");
        }

    }

    @Override
    public void cancle() {

    }
}
