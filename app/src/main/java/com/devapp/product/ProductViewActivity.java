package com.devapp.product;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.devapp.R;

public class ProductViewActivity extends Activity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProductViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //pagerAdapter = new ProductViewPagerAdapter(getFragmentManager());

    }
}
