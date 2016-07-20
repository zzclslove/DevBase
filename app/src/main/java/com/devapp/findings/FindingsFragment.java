package com.devapp.findings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.devapp.R;
import com.devapp.base.MMVCUltraHelper;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.model.ProductDataSource;
import com.shizhefei.mvc.MVCHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class FindingsFragment extends Fragment {

    private Token token;
    private FindingsItemAdapter findingsItemAdapter;
    private MVCHelper<List<Product>> mvcHelper;

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

        Map condition = new HashMap();
        condition.put("intro_type", "is_promote");

        PtrClassicFrameLayout mPtrFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.rotate_header_list_view_frame);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.finding_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        findingsItemAdapter = new FindingsItemAdapter(getActivity().getApplicationContext());
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

        mvcHelper = new MMVCUltraHelper<>(mPtrFrameLayout);
        // 设置数据源
        mvcHelper.setDataSource(new ProductDataSource(condition, token, 1));
        // 设置适配器
        mvcHelper.setAdapter(findingsItemAdapter);
        // 加载数据
        mvcHelper.refresh();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvcHelper.destory();
    }
}
