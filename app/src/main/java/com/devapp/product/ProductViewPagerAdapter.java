package com.devapp.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devapp.home.HomeTabsContentFragment;
import com.devapp.model.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ProductViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"商品","详情","评价"};
    private Product product;

    public ProductViewPagerAdapter(FragmentManager fm, Product product) {
        super(fm);
        this.product = product;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return ProductFragment.newInstance(product);
        }else if(position == 1){
            return ProductDetailFragment.newInstance(product);
        }else{
            return ProductReviewFragment.newInstance(product);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
