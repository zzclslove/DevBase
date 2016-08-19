package com.devapp.user;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.data.ResultData;
import com.devapp.data.ResultMessageData;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/18.
 */
public class RegisterPhoneCodeFragment extends Fragment {

    private TextView remark;
    private Token token;
    private Handler handler;
    private Runnable runnable;
    private TextView getCodeBtn;

    private EditText phoneCodeInput;
    private TextView nextStep;

    static RegisterPhoneCodeFragment newInstance(){
        RegisterPhoneCodeFragment registerPhoneCodeFragment = new RegisterPhoneCodeFragment();
        Bundle bundle = new Bundle();
        registerPhoneCodeFragment.setArguments(bundle);
        return registerPhoneCodeFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            remark.setText("请输入" + token.getPhoneNumber() + "收到的短信验证码:");
            if(token.getSendCodeTimeLeft() == 0){
                sendCode();
            }else{
                handler.postDelayed(runnable, 1000);
            }

        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phonecode, container, false);
        token = (Token) getActivity().getApplicationContext();

        getCodeBtn = (TextView) view.findViewById(R.id.get_code_btn);
        remark = (TextView) view.findViewById(R.id.remark);
        phoneCodeInput = (EditText) view.findViewById(R.id.phonecode_input);
        nextStep = (TextView) view.findViewById(R.id.next_step);

        phoneCodeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String phoneCode = phoneCodeInput.getText().toString();
                if(phoneCode.length() > 0){
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

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(token.getSendCodeTimeLeft() == 0){
                    getCodeBtn.setText("获取验证码");
                    getCodeBtn.setBackgroundResource(R.drawable.corners_bg_login_activity);
                    getCodeBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_ff));
                }else{
                    getCodeBtn.setText("重新发送(" + token.getSendCodeTimeLeft() + ")");
                    handler.postDelayed(this, 1000);
                }
                int timeLeft = token.getSendCodeTimeLeft();
                token.setSendCodeTimeLeft(timeLeft - 1);
            }
        };

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(token.getSendCodeTimeLeft() < 1){
                    sendCode();
                    token.setSendCodeTimeLeft(120);
                    getCodeBtn.setBackgroundResource(R.drawable.corners_bg_login);
                    getCodeBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_500));
                    handler.postDelayed(runnable, 1000);
                }
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneCode = phoneCodeInput.getText().toString().trim();

            }
        });

        return view;
    }

    private void sendCode(){
        String url = token.getRootUrl() + "mobileapi/user.php?action=send_phone_code&mobile=" + token.getPhoneNumber();
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String errorMMessage = e.getMessage();
                Toast.makeText(getActivity(), errorMMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ResultMessageData resultData = gson.fromJson(response.body().string(), ResultMessageData.class);
                if(resultData.getError() == 0){
                    token.setSendCodeTimeLeft(120);
                    handler.postDelayed(runnable, 1000);
                }else{
                    Toast.makeText(getActivity(), resultData.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
