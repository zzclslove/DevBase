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
public class RegisterPasswordFragment extends Fragment {

    static RegisterPasswordFragment newInstance(){
        RegisterPasswordFragment registerPasswordFragment = new RegisterPasswordFragment();
        Bundle bundle = new Bundle();
        registerPasswordFragment.setArguments(bundle);
        return registerPasswordFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_password, container, false);
        return view;
    }

}
