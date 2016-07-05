package com.devapp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devapp.model.Product;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"每日推荐","每周热销","人气新品"};
    private List<List<Product>> productLists;

    public HomePagerAdapter(FragmentManager fm, List<List<Product>> pls) {
        super(fm);
        this.productLists = pls;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeTabsContentFragment.newInstance(productLists.get(position));
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
