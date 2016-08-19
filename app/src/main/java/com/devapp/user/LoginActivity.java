package com.devapp.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.devapp.R;
import com.devapp.base.Token;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginActivity extends FragmentActivity {

    private ViewPager loginStep;
    private LoginStepViewPagerAdapter loginStepViewPagerAdapter;
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        token = (Token) getApplicationContext();

        loginStep = (ViewPager) findViewById(R.id.viewpager_login);
        loginStep.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        loginStepViewPagerAdapter = new LoginStepViewPagerAdapter(getSupportFragmentManager());
        loginStep.setAdapter(loginStepViewPagerAdapter);
        loginStep.setOffscreenPageLimit(1);
        token.setLoginStepViewPager(loginStep);
    }

    @Override
    protected void onDestroy() {
        token.setLoginStepViewPager(null);
        super.onDestroy();
    }
}
