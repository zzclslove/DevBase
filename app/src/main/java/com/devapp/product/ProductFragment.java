package com.devapp.product;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.devapp.R;
import com.devapp.base.ProductSpecSelectorPopWin;
import com.devapp.base.Token;
import com.devapp.model.BuyNote;
import com.devapp.model.Product;
import com.devapp.model.ProductSpec;
import com.devapp.util.DensityUtil;

import java.math.BigDecimal;
import java.util.List;

public class ProductFragment extends Fragment implements ViewPagerEx.OnPageChangeListener{

    private SliderLayout sliderLayout;
    private TextView imagePager;
    private TextView productTitle;
    private TextView productBrief;
    private TextView productPrice;
    private TextView productPriceDou;
    private TextView productOrgPrice;
    private ListView productReviews;
    private TextView commentsCount;
    private TextView commentsRank;
    private TextView nocomments;
    private TextView notesTotal;
    private TableLayout productNotes;
    private TextView nonotes;
    private TextView specsSelected;
    private LinearLayout specSelectContainer;
    private View pageBottom;

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
        final Product product = (Product) getArguments().getSerializable("product");
        List<String> productImages = product.getGoods_img();
        imageCount = productImages.size();

        imagePager = (TextView) view.findViewById(R.id.image_pager);
        productTitle = (TextView) view.findViewById(R.id.product_title);
        productBrief = (TextView) view.findViewById(R.id.product_brief);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        productPriceDou = (TextView) view.findViewById(R.id.product_price_d);
        productOrgPrice = (TextView) view.findViewById(R.id.product_org_price);
        productReviews = (ListView) view.findViewById(R.id.product_reviews);
        commentsCount = (TextView) view.findViewById(R.id.comments_count);
        commentsRank = (TextView) view.findViewById(R.id.comments_rank);
        nocomments = (TextView) view.findViewById(R.id.nocomments);
        notesTotal = (TextView) view.findViewById(R.id.notes_total);
        productNotes = (TableLayout) view.findViewById(R.id.notes_table);
        nonotes = (TextView) view.findViewById(R.id.nonotes);
        specsSelected = (TextView) view.findViewById(R.id.specs_select);
        specSelectContainer = (LinearLayout) view.findViewById(R.id.spec_select_container);
        pageBottom = view.findViewById(R.id.product_page_bottom);

        specSelectContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductSpecSelectorPopWin popSpecSelector = new ProductSpecSelectorPopWin(getActivity(), product);
                popSpecSelector.showAtLocation(getActivity().findViewById(R.id.page_bottom), Gravity.LEFT|Gravity.BOTTOM, 0, 0);
            }
        });

        if(product.getSpecs() == null || product.getSpecs().size() == 0){
            specSelectContainer.setVisibility(View.GONE);
        }else{
            specSelectContainer.setVisibility(View.VISIBLE);
            String specSelectDesc = "请选择 ";
            int index = 0;
            for(ProductSpec productSpec: product.getSpecs()){
                index ++;
                specSelectDesc += productSpec.getName();
                if(index != product.getSpecs().size()){
                    specSelectDesc += "，";
                }
            }
            specsSelected.setText(specSelectDesc);
        }

        if(product.getNotes() == null || product.getNotes().size() == 0){
            productNotes.setVisibility(View.GONE);
            nonotes.setVisibility(View.VISIBLE);
        }else{
            productNotes.setVisibility(View.VISIBLE);
            nonotes.setVisibility(View.GONE);
            int index = 0;
            for(BuyNote note: product.getNotes()){
                index ++;
                int tvPadding = DensityUtil.dip2px(getActivity(), 3);
                int border = DensityUtil.dip2px(getActivity(), new Float("0.5"));
                TableRow tableRow = new TableRow(getActivity());
                TextView tvName = new TextView(getActivity());
                TextView tvNum = new TextView(getActivity());
                TextView tvTime = new TextView(getActivity());
                TextView tvStatus = new TextView(getActivity());
                if(note.getUser_name() == null || note.getUser_name().length() == 0){
                    tvName.setText("匿名用户");
                }else{
                    tvName.setText(note.getUser_name());
                }
                tvName.setPadding(0, tvPadding, 0, tvPadding);
                tvName.setTextSize(12);
                tvName.setTextColor(getResources().getColor(R.color.black_3c));
                tvNum.setText(note.getGoods_number());
                tvNum.setGravity(Gravity.CENTER);
                tvNum.setPadding(0, tvPadding, 0, tvPadding);
                tvNum.setTextSize(12);
                tvNum.setTextColor(getResources().getColor(R.color.black_3c));
                tvTime.setText(note.getAdd_time());
                tvTime.setGravity(Gravity.CENTER);
                tvTime.setPadding(0, tvPadding, 0, tvPadding);
                tvTime.setTextSize(12);
                tvTime.setTextColor(getResources().getColor(R.color.black_3c));
                tvStatus.setText("成交");
                tvStatus.setGravity(Gravity.CENTER);
                tvStatus.setPadding(0, tvPadding, 0, tvPadding);
                tvStatus.setTextSize(12);
                tvStatus.setTextColor(getResources().getColor(R.color.black_3c));
                tableRow.addView(tvName);
                tableRow.addView(tvNum);
                tableRow.addView(tvTime);
                tableRow.addView(tvStatus);
                productNotes.addView(tableRow);
                if(index != product.getNotes().size()){
                    View borderView = new View(getActivity());
                    borderView.setBackgroundResource(R.color.black_border);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, border);
                    borderView.setLayoutParams(layoutParams);
                    productNotes.addView(borderView);
                }
            }
        }

        if(product.getComments() == null || product.getComments().size() == 0){
            nocomments.setVisibility(View.VISIBLE);
            productReviews.setVisibility(View.GONE);
        }else{
            productReviews.setAdapter(new ProductReviewAdapter(getActivity(), product.getComments()));
            DensityUtil.setPullLvHeight(productReviews);
        }

        commentsCount.setText("(" + product.getComments_count() + ")");
        commentsRank.setText(Math.ceil(product.getComment_rank()/5*100) + " %");

        notesTotal.setText(product.getNotes_count() + "");

        productTitle.setText(product.getName());
        if(product.getBrief().length() == 0){
            productBrief.setVisibility(View.GONE);
        }else{
            productBrief.setText(product.getBrief());
        }

        BigDecimal price = new BigDecimal(product.getShop_price());
        BigDecimal orgPrice = new BigDecimal("0");
        if(product.getPromote_price().length() > 0){
            orgPrice = new BigDecimal(product.getShop_price());
            price = new BigDecimal(product.getPromote_price());
        }

        int intPrice = price.setScale(2).intValue();
        int decimalPrice = Integer.parseInt(new java.text.DecimalFormat("0").format(((price.setScale(2).doubleValue() - intPrice) * 100)));
        if(decimalPrice != 0){
            productPrice.setText(intPrice + ".");
            productPriceDou.setText(decimalPrice + "");
        }else{
            productPrice.setText(intPrice + ".");
            productPriceDou.setText("00");
        }

        if(orgPrice.compareTo(BigDecimal.ZERO) != 0){
            productOrgPrice.setText("￥" + orgPrice + "");
            productOrgPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        }

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
