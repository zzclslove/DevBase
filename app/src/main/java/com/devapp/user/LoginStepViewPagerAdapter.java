package com.devapp.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginStepViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;

    public LoginStepViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return LoginFragment.newInstance();
        }else if(position == 1){
            return RegisterPhoneFragment.newInstance();
        }else if(position == 2){
            return RegisterPhoneCodeFragment.newInstance();
        }else{
            return RegisterPasswordFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
