package com.devapp.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devapp.R;
import com.devapp.home.HomeProductAdapter;
import com.devapp.model.Category;
import com.devapp.model.Product;

import java.io.Serializable;
import java.util.List;

public class CategoryPageContentFragment extends Fragment {

    public static final String ARG_DATA = "ARG_DATA";
    private List<Category> categoryList;

    public static CategoryPageContentFragment newInstance(List<Category> categoryList) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, (Serializable)categoryList);
        CategoryPageContentFragment pageFragment = new CategoryPageContentFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryList = (List<Category>)getArguments().getSerializable(ARG_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_category_itemlist, container, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        view.setLayoutManager(layoutManager);
        CategoryItemAdapter adapter = new CategoryItemAdapter(categoryList);
        adapter.setOnRecyclerViewListener(new CategoryItemAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        view.setAdapter(adapter);
        return view;
    }

}
