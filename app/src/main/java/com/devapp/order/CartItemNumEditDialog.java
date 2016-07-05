package com.devapp.order;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;

public class CartItemNumEditDialog extends Dialog {

    public CartItemNumEditDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private int itemNum;
        private TextView tvNum;
        private int position;
        private Token token;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context, int itemNum, TextView tvNum, int position) {
            this.itemNum = itemNum;
            this.tvNum = tvNum;
            this.position = position;
            this.context = context;
            this.token = (Token) context.getApplicationContext();
        }

        public Builder setPositiveButton(DialogInterface.OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(DialogInterface.OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CartItemNumEditDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CartItemNumEditDialog dialog = new CartItemNumEditDialog(context, R.style.CartItemNumEditDialog);
            View layout = inflater.inflate(R.layout.dialog_cartitem_numedit, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            final EditText editText = (EditText) layout.findViewById(R.id.cart_item_dialog_numedit);
            editText.setText(itemNum + "");
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
            });
            layout.findViewById(R.id.cart_item_dialog_reduce).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int productNum = Integer.parseInt(editText.getText().toString());
                    if(productNum > 1){
                        editText.setText((productNum - 1) + "");
                    }
                }
            });
            layout.findViewById(R.id.cart_item_dialog_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int productNum = Integer.parseInt(editText.getText().toString());
                    editText.setText((productNum + 1) + "");
                }
            });
            layout.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(editText.getText().length() > 0){
                        tvNum.setText(editText.getText().toString());
                        token.getInitData().getCart().getCartProducts().get(position).setNum(Integer.parseInt(editText.getText().toString()));
                    }
                    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
            });
            layout.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
