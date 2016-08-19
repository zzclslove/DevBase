package com.devapp.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.Toast;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.task.GetProductDataTask;
import com.shizhefei.task.Callback;
import com.shizhefei.task.Code;
import com.shizhefei.task.TaskHelper;

public class ProductViewActivity extends FragmentActivity {

    private Token token;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProductViewPagerAdapter pagerAdapter;
    private TaskHelper<Product, String> productViewDataLoaderHelper;

    private Callback<Product, String> productViewDataLoaderCallback = new Callback<Product, String>() {
        @Override
        public void onPreExecute() {

        }

        @Override
        public void onProgressUpdate(int percent, long current, long total, Object exraData) {

        }

        @Override
        public void onPostExecute(Code code, Exception exception, Product success, String fail) {
            switch (code) {
                case FAIL:
                case EXCEPTION:
                    if (TextUtils.isEmpty(fail)) {
                        Toast.makeText(getApplicationContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case SUCESS:
                    tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
                    viewPager = (ViewPager) findViewById(R.id.viewpager);
                    pagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager(), success);
                    viewPager.setAdapter(pagerAdapter);
                    tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
                    tabLayout.setupWithViewPager(viewPager);
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);
        token = (Token) getApplicationContext();

        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        String url = token.getRootUrl() + "mobileapi/goods.php?action=get_goods_info";

        productViewDataLoaderHelper = new TaskHelper<Product, String>();
        productViewDataLoaderHelper.setTask(new GetProductDataTask(url, pid));
        productViewDataLoaderHelper.setCallback(productViewDataLoaderCallback);
        productViewDataLoaderHelper.execute();
    }
}
