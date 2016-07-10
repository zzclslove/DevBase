package com.devapp.product;

import android.app.Activity;
import android.os.Bundle;

import com.devapp.R;

/**
 * Created by Administrator on 2016/7/10.
 */
public class ProductListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        Bundle bundle = this.getIntent().getExtras();
        String catId = bundle.getString("catId");


    }
}
