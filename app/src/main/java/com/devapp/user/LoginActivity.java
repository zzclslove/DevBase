package com.devapp.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

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
        loginStepViewPagerAdapter = new LoginStepViewPagerAdapter(getSupportFragmentManager());
        loginStep.setAdapter(loginStepViewPagerAdapter);
        token.setLoginStepViewPager(loginStep);
    }

}
