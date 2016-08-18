package com.devapp.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devapp.R;

/**
 * Created by Administrator on 2016/8/18.
 */
public class RegisterPhoneFragment extends Fragment {

    static RegisterPhoneFragment newInstance(){
        RegisterPhoneFragment registerPhoneFragment = new RegisterPhoneFragment();
        Bundle bundle = new Bundle();
        registerPhoneFragment.setArguments(bundle);
        return registerPhoneFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phone, container, false);
        return view;
    }

}
