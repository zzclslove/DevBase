package com.devapp.product;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.MMVCUltraHelper;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.model.ProductDataSource;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.imp.DefaultLoadViewFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class ProductListActivity extends Activity {

    private String orderBy = "goods_id";
    private String order = "DESC";
    private String catId;
    private EditText searchInput;
    private ImageView backBtn;
    private TextView sortDefault;
    private TextView sortSales;
    private TextView sortPrice;
    private TextView sortTime;
    private ImageView sortUp;
    private ImageView sortDown;

    private PtrClassicFrameLayout mPtrFrameLayout;
    private MVCHelper<List<Product>> mvcHelper;

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        token = (Token) getApplicationContext();

        searchInput = (EditText) findViewById(R.id.search_edit_text);
        backBtn = (ImageView) findViewById(R.id.search_back);
        sortDefault = (TextView) findViewById(R.id.sort_default);
        sortSales = (TextView) findViewById(R.id.sort_sales);
        sortPrice = (TextView) findViewById(R.id.sort_price);
        sortTime = (TextView) findViewById(R.id.sort_time);
        sortUp = (ImageView) findViewById(R.id.sort_up);
        sortDown = (ImageView) findViewById(R.id.sort_down);

        Bundle bundle = this.getIntent().getExtras();
        catId = bundle.getString("catId");

        mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        recyclerView = (RecyclerView) findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        productListAdapter = new ProductListAdapter(getApplicationContext());
        productListAdapter.setOnRecyclerViewListener(new ProductListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        MVCHelper.setLoadViewFractory(new DefaultLoadViewFactory());
        mvcHelper = new MMVCUltraHelper<>(mPtrFrameLayout);
        showProductResultList();

        sortDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelected();
                sortDefault.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.activityColor));
                orderBy = "goods_id";
                order = "DESC";
                showProductResultList();
            }
        });

        sortSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelected();
                sortSales.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.activityColor));
                orderBy = "goods_sales";
                order = "DESC";
                showProductResultList();
            }
        });

        sortPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelected();
                sortPrice.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.activityColor));
                orderBy = "shop_price";
                if(order.equals("DESC")){
                    order = "ASC";
                    sortUp.setImageResource(R.mipmap.sort_up_activity);
                    sortDown.setImageResource(R.mipmap.sort_down);
                }else{
                    order = "DESC";
                    sortUp.setImageResource(R.mipmap.sort_up);
                    sortDown.setImageResource(R.mipmap.sort_down_activity);
                }
                showProductResultList();
            }
        });

        sortTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelected();
                sortTime.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.activityColor));
                orderBy = "add_time";
                order = "DESC";
                showProductResultList();
            }
        });

    }

    private void showProductResultList(){
        Map condition = new HashMap();
        condition.put("sort_by", orderBy);
        condition.put("sort_order", order);
        condition.put("cat_id", catId);

        mvcHelper.setDataSource(new ProductDataSource(condition, token));
        mvcHelper.setAdapter(productListAdapter);
        mvcHelper.refresh();
    }

    private void removeSelected(){
        sortDefault.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_3c));
        sortSales.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_3c));
        sortPrice.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_3c));
        sortTime.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black_3c));
        sortUp.setImageResource(R.mipmap.sort_up);
        sortDown.setImageResource(R.mipmap.sort_down);
    }
}
