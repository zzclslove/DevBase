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
    private TextView tvhourLeft;
    private TextView tvMiniuteLeft;
    private TextView tvSecondLeft;
    private Token token;

    private long endTimeMillis;
    private Handler handler;

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
        handler = new Handler();

        tvhourLeft = (TextView) view.findViewById(R.id.tv_hour_left);
        tvMiniuteLeft = (TextView) view.findViewById(R.id.tv_miniute_left);
        tvSecondLeft = (TextView) view.findViewById(R.id.tv_second_left);

        String endTimeStr = (String) token.getFindingProductList().get("endTime");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr));
            endTimeMillis = c.getTimeInMillis();
            long currentTimeMillisTime = System.currentTimeMillis();
            if(endTimeMillis - currentTimeMillisTime > 0){
                handler.postDelayed(runnable, 1000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.finding_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        FindingsItemAdapter findingsItemAdapter = new FindingsItemAdapter(getActivity().getApplicationContext(), (List < Product >) token.getFindingProductList().get("productList"));
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


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long currentTimeMillisTime = System.currentTimeMillis();
            long leftTimeMillis = endTimeMillis - currentTimeMillisTime;
            getLeftStr(leftTimeMillis);
            if(leftTimeMillis  > 0){
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void getLeftStr(long leftTime){
        long hour = leftTime / (3600*1000);
        long miniute = (leftTime % (3600*1000)) / (60*1000);
        long second = (leftTime % (60*1000)) / 1000;
        String miniuteStr = miniute < 10 ? ("0" + miniute) : (miniute + "");
        String secondStr = second < 10 ? ("0" + second) : (second + "");


        tvhourLeft.setText(hour + "");
        tvMiniuteLeft.setText(miniuteStr);
        tvSecondLeft.setText(secondStr);
    }

}
