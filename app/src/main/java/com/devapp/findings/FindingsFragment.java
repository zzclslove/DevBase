package com.devapp.findings;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.devapp.R;

import com.devapp.base.Token;
import com.devapp.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FindingsFragment extends Fragment {

    PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private FindingsItemAdapter findingsItemAdapter;
    private RecyclerAdapterWithHF mAdapter;
    private Token token;
    private int currentPage = 1;
    Handler handler = new Handler();
    private List<Product> findingProductList = new ArrayList<>();

    public static FindingsFragment newInstance(String s) {
        FindingsFragment newFragment = new FindingsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", s);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_findings, container, false);
        token = (Token) getActivity().getApplicationContext();

        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.finding_recycler_view_frame);
        recyclerView = (RecyclerView) view.findViewById(R.id.finding_list);
        findingsItemAdapter = new FindingsItemAdapter(getActivity().getApplicationContext(), findingProductList);
        mAdapter = new RecyclerAdapterWithHF(findingsItemAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);


        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPage = 1;
                        findingProductList.clear();
                        findingProductList.addAll(token.getInitData().getFindingProductList());
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                    }
                }, 1000);
            }
        });

        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPage ++;
                        RequestParams paramsCategory = new RequestParams(token.getRootUrl() + "api/index.php");
                        paramsCategory.addQueryStringParameter("action", "get_more_promotion_products");
                        paramsCategory.addQueryStringParameter("page", currentPage + "");
                        x.http().get(paramsCategory, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                List<Product> productList = gson.fromJson(result, new TypeToken<List<Product>>() {}.getType());
                                findingProductList.addAll(productList);
                                mAdapter.notifyDataSetChanged();
                                ptrClassicFrameLayout.loadMoreComplete(true);
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {}
                            @Override
                            public void onCancelled(CancelledException cex) { }
                            @Override
                            public void onFinished() { }
                        });
                    }
                }, 1000);
            }
        });

        findingsItemAdapter.setOnRecyclerViewListener(new FindingsItemAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }

            @Override
            public void onButtonClick(int position) {

            }
        });

        return view;
    }

}
