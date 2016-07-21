package com.devapp.task;

import com.devapp.model.Product;
import com.shizhefei.task.Data;
import com.shizhefei.task.ProgressSender;
import com.shizhefei.task.Task;

/**
 * Created by Administrator on 2016/7/21.
 */
public class GetProductDataTask implements Task<Product, String> {

    private int productId;

    public GetProductDataTask(int productId) {
        super();
        this.productId = productId;
    }

    @Override
    public Data<Product, String> execute(ProgressSender progressSender) throws Exception {
        return null;
    }

    @Override
    public void cancle() {

    }
}
