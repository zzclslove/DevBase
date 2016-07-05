package com.devapp.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;

public class CategoryFragment extends Fragment {

    private TextView pageTitle;
    private TabLayout categoryTabLayout;
    private ViewPager categoryViewPager;
    private CategoryPagerAdapter categoryPagerAdapter;
    private Token token;

    public static CategoryFragment newInstance(String s) {
        CategoryFragment newFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", s);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        token = (Token) getActivity().getApplicationContext();

        pageTitle = (TextView) view.findViewById(R.id.page_name);
        Bundle args = getArguments();
        pageTitle.setText(args.getString("name"));

        categoryTabLayout = (TabLayout) view.findViewById(R.id.category_sliding_tabs);
        categoryViewPager = (ViewPager) view.findViewById(R.id.category_viewpager);
        categoryPagerAdapter = new CategoryPagerAdapter(getChildFragmentManager(), token.getInitData().getCategoryList());
        categoryViewPager.setAdapter(categoryPagerAdapter);
        categoryTabLayout.setupWithViewPager(categoryViewPager);

        return view;
    }
}
