package com.devapp.user;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devapp.R;
import com.devapp.base.Token;

/**
 * Created by Administrator on 2016/8/18.
 */
public class RegisterPhoneFragment extends Fragment {

    private EditText phoneInput;
    private TextView nextStep;
    private ImageView pageBack;
    private Token token;

    static RegisterPhoneFragment newInstance(){
        RegisterPhoneFragment registerPhoneFragment = new RegisterPhoneFragment();
        Bundle bundle = new Bundle();
        registerPhoneFragment.setArguments(bundle);
        return registerPhoneFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phone, container, false);
        token = (Token) getActivity().getApplicationContext();

        phoneInput = (EditText) view.findViewById(R.id.phone_input);
        nextStep = (TextView) view.findViewById(R.id.next_step);
        pageBack = (ImageView) view.findViewById(R.id.page_back);
        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(phoneInput.getText().toString().length() > 0){
                    nextStep.setBackgroundResource(R.drawable.corners_bg_login_activity);
                    nextStep.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_ff));
                }else{
                    nextStep.setBackgroundResource(R.drawable.corners_bg_login);
                    nextStep.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_500));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token.getLoginStepViewPager().setCurrentItem(0);
            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telRegex = "[1][358]\\d{9}";
                String phoneNumber = phoneInput.getText().toString();
                if (!TextUtils.isEmpty(phoneNumber)){
                    if(phoneNumber.matches(telRegex)){
                        token.setPhoneNumber(phoneInput.getText().toString());
                        token.getLoginStepViewPager().setCurrentItem(2);
                    }else{
                        Toast.makeText(getActivity(), "请填写正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

}
