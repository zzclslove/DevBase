package com.devapp.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devapp.R;
import com.devapp.model.Product;

public class ProductDetailFragment extends Fragment {

    static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment newFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Product product = (Product) savedInstanceState.getSerializable("product");

        return view;
    }

}
