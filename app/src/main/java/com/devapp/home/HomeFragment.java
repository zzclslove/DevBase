package com.devapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.devapp.R;
import com.devapp.base.MyScrollview;
import com.devapp.base.SearchActivity;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.model.Topic;
import com.devapp.util.DensityUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout sliderLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomePagerAdapter pagerAdapter;
    private RelativeLayout homeTopContainer;
    private LinearLayout homeTopSearch;
    private LinearLayout homeTopSearchIcon;
    private TextView homeTopSearchPlaceHolder;
    private Token token;

    public static HomeFragment newInstance(String s) {
        HomeFragment newFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", s);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        token = (Token) getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        int heightSlider = token.getWindowWidth()*400/780;
        RelativeLayout.LayoutParams paramsSlider = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightSlider);
        sliderLayout.setLayoutParams(paramsSlider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        List<Topic> topicList = token.getInitData().getTopicList();
        for (Topic topic:topicList) {
            url_maps.put(topic.getTitle(),token.getRootUrl() + topic.getTopic_img());
        }
        int i = 0;
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(view.getContext());
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",topicList.get(i).getTopic_id() + "");
            sliderLayout.addSlider(textSliderView);
            i++;
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);

        //获取三个tab的数据
        List<List<Product>> homeProductLists = new ArrayList<>();
        homeProductLists.add(token.getInitData().getRecommendProducts().getBestProductList());
        homeProductLists.add(token.getInitData().getRecommendProducts().getHotProductList());
        homeProductLists.add(token.getInitData().getRecommendProducts().getNewProductList());

        pagerAdapter = new HomePagerAdapter(this.getChildFragmentManager(), homeProductLists);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        int productListItemHeight = (int) ((token.getWindowWidth() - DensityUtil.dip2px(getContext(), 50)) / (2 * token.getProductImageScale())) + DensityUtil.dip2px(getContext(), 73);
        int height = token.getInitData().getRecommendProducts().getBestProductList().size() / 2 * productListItemHeight;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        viewPager.setLayoutParams(params);

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        homeTopContainer = (RelativeLayout) view.findViewById(R.id.home_top_container);
        homeTopSearch = (LinearLayout) view.findViewById(R.id.home_top_search_input);
        homeTopSearchIcon = (LinearLayout)view.findViewById(R.id.home_top_search_icon);
        homeTopSearchIcon.getBackground().setAlpha(160);
        homeTopSearchPlaceHolder = (TextView) view.findViewById(R.id.home_top_search_placeholder);
        homeTopSearchPlaceHolder.setText("大家都在搜：宝贝");
        final MyScrollview myScrollview = (MyScrollview) view.findViewById(R.id.home_scrollview);
        myScrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override public void onScrollChanged() {
                if(myScrollview.getScrollY() > 10){
                    homeTopContainer.setBackgroundResource(R.drawable.home_top_container);
                    homeTopContainer.getBackground().setAlpha(210);
                    homeTopSearch.setBackgroundResource(R.drawable.home_top_search2);
                    homeTopSearch.getBackground().setAlpha(210);
                }else{
                    homeTopContainer.setBackgroundResource(0);
                    homeTopSearch.setBackgroundResource(R.drawable.home_top_search);
                }
            }
        });

        homeTopContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SearchActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getView().getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {}
    @Override
    public void onPageScrollStateChanged(int state) {}

}
