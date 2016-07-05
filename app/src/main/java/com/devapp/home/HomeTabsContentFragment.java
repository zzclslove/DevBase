package com.devapp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devapp.R;
import com.devapp.model.Product;

import java.io.Serializable;
import java.util.List;

public class HomeTabsContentFragment extends Fragment {

    public static final String ARG_DATA = "ARG_DATA";
    private List<Product> products;

    public static HomeTabsContentFragment newInstance(List<Product> productLists) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, (Serializable)productLists);
        HomeTabsContentFragment pageFragment = new HomeTabsContentFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products = (List<Product>)getArguments().getSerializable(ARG_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_home_itemlist, container, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        view.setLayoutManager(layoutManager);
        HomeProductAdapter adapter = new HomeProductAdapter(getContext(), products);
        adapter.setOnRecyclerViewListener(new HomeProductAdapter.OnRecyclerViewListener() {
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
