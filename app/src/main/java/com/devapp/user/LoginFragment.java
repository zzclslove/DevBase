package com.devapp.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.devapp.R;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginFragment extends Fragment {

    private ImageView passwordHide;
    private ImageView passwordShow;
    private EditText passwordEditor;
    private EditText phoneEditor;

    static LoginFragment newInstance(){
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        passwordHide = (ImageView) view.findViewById(R.id.password_hide);
        passwordShow = (ImageView) view.findViewById(R.id.password_show);
        passwordEditor = (EditText) view.findViewById(R.id.password_input);
        phoneEditor = (EditText) view.findViewById(R.id.phone_input);

        phoneEditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(phoneEditor.getText().toString().length() == 0 || passwordEditor.getText().toString().length() == 0){

                }
                return false;
            }
        });
        passwordHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEditor.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                passwordHide.setVisibility(View.INVISIBLE);
                passwordShow.setVisibility(View.VISIBLE);
            }
        });
        passwordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEditor.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordHide.setVisibility(View.VISIBLE);
                passwordShow.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

}
