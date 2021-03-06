package com.devapp.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devapp.R;
import com.devapp.model.Product;

public class ProductReviewFragment extends Fragment {

    static ProductReviewFragment newInstance(Product product) {
        ProductReviewFragment newFragment = new ProductReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_review, container, false);

        Product product = (Product) getArguments().getSerializable("product");

        return view;
    }

}
