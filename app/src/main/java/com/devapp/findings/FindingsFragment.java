package com.devapp.findings;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FindingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Token token;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.finding_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        FindingsItemAdapter findingsItemAdapter = new FindingsItemAdapter(getActivity().getApplicationContext(), token.getInitData().getFindingProductList());
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
        recyclerView.setAdapter(findingsItemAdapter);
        return view;
    }

}
