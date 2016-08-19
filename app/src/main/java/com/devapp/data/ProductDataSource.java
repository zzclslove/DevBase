package com.devapp.data;

import com.devapp.base.Token;
import com.devapp.model.Product;
import com.google.gson.Gson;
import com.shizhefei.mvc.IDataSource;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ProductDataSource implements IDataSource<List<Product>> {
    private int page = 1;
    private int maxPage = 5;
    private Map<String, String> condition;
    private Token token;
    private int columNum;

    public ProductDataSource(Map<String, String> condition, Token token, int columNum){
        this.condition = condition;
        this.token = token;
        this.columNum = columNum;
    }

    @Override
    public List<Product> refresh() throws Exception {
        return loadProducts(1, condition, token);
    }

    @Override
    public List<Product> loadMore() throws Exception {
        return loadProducts(page + 1, condition, token);
    }

    private List<Product> loadProducts(final int currentPage, Map<String, String> condition, Token token) throws Exception {

        String url = token.getRootUrl() + "mobileapi/goods.php?action=get_goods_list&page=" + currentPage;
        for (String k : condition.keySet())
        {
            url += "&" + k + "=" + condition.get(k);
        }
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        Gson gson = new Gson();
        ResultProducts resultData = gson.fromJson(response.body().string(), ResultProducts.class);
        page = currentPage;
        maxPage = resultData.getPage_count();
        List<Product> productList = resultData.getGoods();
        if(productList.size() % columNum != 0){
            productList.subList(productList.size() / columNum * columNum, productList.size()).clear();
        }
        return productList;
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

}
