package com.devapp.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private List<List<Category>> categoryLists;
    private int pageCount;
    private List<String> tabTitles;

    public CategoryPagerAdapter(FragmentManager fm, List<Category> categoryList) {
        super(fm);
        tabTitles = new ArrayList<>();
        categoryLists = new ArrayList<>();
        pageCount = 0;
        for (Category category : categoryList){
            if(category.getParent_id() == 0){
                tabTitles.add(category.getCat_name());
                pageCount ++;
                List<Category> currentCategoryList = new ArrayList<>();
                for (Category child : categoryList){
                    if(child.getParent_id() == category.getCat_id()){
                        currentCategoryList.add(child);
                    }
                }
                categoryLists.add(currentCategoryList);
            }

        }
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryPageContentFragment.newInstance(categoryLists.get(position));
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

}
