package com.devapp.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginFragment extends Fragment {

    private ImageView passwordHide;
    private ImageView passwordShow;
    private EditText passwordEditor;
    private EditText phoneEditor;
    private TextView submitBtn;
    private TextView registerByPhone;
    private TextView findPasswordBack;
    private ImageView pageClose;
    private Token token;

    static LoginFragment newInstance(){
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        token = (Token) getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        passwordHide = (ImageView) view.findViewById(R.id.password_hide);
        passwordShow = (ImageView) view.findViewById(R.id.password_show);
        passwordEditor = (EditText) view.findViewById(R.id.password_input);
        phoneEditor = (EditText) view.findViewById(R.id.phone_input);
        submitBtn = (TextView) view.findViewById(R.id.login_btn);
        registerByPhone = (TextView) view.findViewById(R.id.register_byphone);
        findPasswordBack = (TextView) view.findViewById(R.id.find_password_back);
        pageClose = (ImageView) view.findViewById(R.id.page_close);

        registerByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token.getLoginStepViewPager().setCurrentItem(1);
            }
        });
        pageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        phoneEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(phoneEditor.getText().toString().length() == 0 || passwordEditor.getText().toString().length() == 0){
                    submitBtn.setBackgroundResource(R.drawable.corners_bg_login);
                    submitBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_500));
                }else{
                    submitBtn.setBackgroundResource(R.drawable.corners_bg_login_activity);
                    submitBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_ff));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        passwordEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(phoneEditor.getText().toString().length() == 0 || passwordEditor.getText().toString().length() == 0){
                    submitBtn.setBackgroundResource(R.drawable.corners_bg_login);
                    submitBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_500));
                }else{
                    submitBtn.setBackgroundResource(R.drawable.corners_bg_login_activity);
                    submitBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_ff));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
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
