package com.devapp.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Product;

import java.util.List;

public class ProductFragment extends Fragment implements ViewPagerEx.OnPageChangeListener{

    private SliderLayout sliderLayout;
    private TextView imagePager;
    private TextView productTitle;

    private int imageCount;
    private Token token;

    static ProductFragment newInstance(Product product) {
        ProductFragment newFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        token = (Token) getActivity().getApplicationContext();
        Product product = (Product) getArguments().getSerializable("product");
        List<String> productImages = product.getGoods_img();
        imageCount = productImages.size();

        imagePager = (TextView) view.findViewById(R.id.image_pager);
        productTitle = (TextView) view.findViewById(R.id.product_title);

        productTitle.setText(product.getName());

        RelativeLayout silderContainer = (RelativeLayout) view.findViewById(R.id.slider_container);
        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        int heightSlider = token.getWindowWidth();
        LinearLayout.LayoutParams paramsSliderContainer = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightSlider);
        RelativeLayout.LayoutParams paramsSlider = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, heightSlider);
        silderContainer.setLayoutParams(paramsSliderContainer);
        sliderLayout.setLayoutParams(paramsSlider);
        for(String imageUrl: productImages){
            DefaultSliderView sliderView = new DefaultSliderView(view.getContext());
            sliderView.image(token.getRootUrl() + imageUrl);
            sliderLayout.addSlider(sliderView);
        }
        sliderLayout.stopAutoCycle();
        sliderLayout.getPagerIndicator().setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        imagePager.setText("1/" + imageCount);
        sliderLayout.addOnPageChangeListener(this);

        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        imagePager.setText((position + 1) + "/" + imageCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
