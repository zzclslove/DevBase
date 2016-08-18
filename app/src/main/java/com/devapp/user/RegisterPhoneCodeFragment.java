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
public class RegisterPhoneCodeFragment extends Fragment {

    static RegisterPhoneCodeFragment newInstance(){
        RegisterPhoneCodeFragment registerPhoneCodeFragment = new RegisterPhoneCodeFragment();
        Bundle bundle = new Bundle();
        registerPhoneCodeFragment.setArguments(bundle);
        return registerPhoneCodeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phonecode, container, false);
        return view;
    }

}
