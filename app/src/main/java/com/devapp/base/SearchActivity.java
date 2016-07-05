package com.devapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private List<String> hotSearchWords;
    private List<String> historySearchWords;
    private LinearLayout hotSearchWordsContainer;
    private LinearLayout historySearchWordContainer;
    private TextView searchClearBtn;
    private ImageView pageBack;
    private Activity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        self = this;

        int pl = DensityUtil.dip2px(this, 10);
        int pl2 = DensityUtil.dip2px(this, 14);
        int pt = DensityUtil.dip2px(this, 2);

        hotSearchWords = new ArrayList<>();
        hotSearchWords.add("探路者");hotSearchWords.add("李宁");hotSearchWords.add("奥迪双钻");
        hotSearchWords.add("儿童手表");hotSearchWords.add("余罪");hotSearchWords.add("干花");
        hotSearchWords.add("汽车香水座");hotSearchWords.add("学习桌");hotSearchWords.add("e卡");
        hotSearchWords.add("榨汁机");hotSearchWords.add("战网");hotSearchWords.add("妈妈壹选");
        hotSearchWordsContainer = (LinearLayout) findViewById(R.id.search_hot_search_word);
        for (String hotWord : hotSearchWords){
            TextView hotWordTextView = new TextView(this);
            hotWordTextView.setText(hotWord);
            hotWordTextView.setBackgroundResource(R.drawable.search_box_background);
            hotWordTextView.setPadding(pl, pt, pl, pt);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = pl;
            hotWordTextView.setLayoutParams(params);
            hotSearchWordsContainer.addView(hotWordTextView);
        }

        historySearchWords = new ArrayList<>();
        historySearchWords.add("布书");historySearchWords.add("阿泰数码");historySearchWords.add("美壳壳");
        historySearchWords.add("榨汁机");historySearchWords.add("学习桌");historySearchWords.add("战网");
        historySearchWordContainer = (LinearLayout) findViewById(R.id.search_history_container);
        for (String historyWord : historySearchWords){
            TextView historyWordTextView = new TextView(this);
            historyWordTextView.setText(historyWord);
            historyWordTextView.setBackgroundResource(R.drawable.list_item_click_selector);
            historyWordTextView.setPadding(pl2, pl, pl, pl);
            historyWordTextView.setTextColor(getResources().getColor(R.color.black_3c));
            historyWordTextView.setClickable(true);
            historyWordTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            historySearchWordContainer.addView(historyWordTextView);
        }

        searchClearBtn = (TextView) findViewById(R.id.search_clear_btn);
        searchClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pageBack = (ImageView) findViewById(R.id.search_back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.finish();
            }
        });

    }
}
