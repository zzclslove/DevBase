package com.devapp.model;

import com.devapp.base.Token;
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

    public ProductDataSource(Map<String, String> condition, Token token){
        this.condition = condition;
        this.token = token;
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

        String url = token.getRootUrl() + "api/goods.php?action=get_goods_list&page=" + currentPage;
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
        return resultData.getGoods();
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

}
