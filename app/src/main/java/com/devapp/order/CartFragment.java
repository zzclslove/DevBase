package com.devapp.order;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;

public class CartFragment extends Fragment {

    private Token token;
    private RecyclerView recyclerView;
    private TextView cartEditBtn;

    public static CartFragment newInstance(String s) {
        CartFragment newFragment = new CartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", s);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        token = (Token) getActivity().getApplicationContext();

        TextView pageTitle = (TextView) view.findViewById(R.id.page_name);
        Bundle args = getArguments();
        pageTitle.setText(args.getString("name"));

        recyclerView = (RecyclerView) view.findViewById(R.id.cart_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CartItemEditAdapter cartItemEditAdapter = new CartItemEditAdapter(getActivity().getApplicationContext(), token.getCart());

        final CartItemAdapter.OnRecyclerViewListener onRecyclerViewListener = new CartItemAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {}
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
            @Override
            public void onSelectorChanged(int position, View v) {}
        };
        final CartItemEditAdapter.OnRecyclerViewListener onRecyclerViewEditListener = new CartItemEditAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {}
            @Override
            public boolean onItemLongClick(int position) {return false;}
            @Override
            public void onButtonAddClick(int position, RecyclerView.ViewHolder v) {
                TextView tvNum = (TextView) v.itemView.findViewById(R.id.cart_item_num);
                int num = Integer.parseInt(tvNum.getText().toString());
                tvNum.setText((num + 1) + "");
                v.itemView.findViewById(R.id.cart_item_reduce).setEnabled(true);
                token.getCart().getCartProducts().get(position).setNum(num + 1);
            }
            @Override
            public void onButtonReduceClick(int position, RecyclerView.ViewHolder v) {
                TextView tvNum = (TextView) v.itemView.findViewById(R.id.cart_item_num);
                int num = Integer.parseInt(tvNum.getText().toString());
                if(num > 1){
                    tvNum.setText((num - 1) + "");
                    token.getCart().getCartProducts().get(position).setNum(num - 1);
                }else{
                    v.itemView.findViewById(R.id.cart_item_reduce).setEnabled(false);
                }

            }
            @Override
            public void onNumEditClick(int position, RecyclerView.ViewHolder v) {
                TextView tvNum = (TextView) v.itemView.findViewById(R.id.cart_item_num);
                CartItemNumEditDialog.Builder builder = new CartItemNumEditDialog.Builder(getContext(), Integer.parseInt(tvNum.getText().toString()), tvNum, position);
                builder.setPositiveButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
            @Override
            public void onSelectorChanged(int position, View v) {}
            @Override
            public void onDeleteBtnClick(int position) {
                token.getCart().getCartProducts().remove(position);
                cartItemEditAdapter.notifyItemRemoved(position);
                cartItemEditAdapter.notifyItemRangeChanged(0, token.getCart().getCartProducts().size());
            }
            @Override
            public void onPropsEditBtnClick(int position, View v) {}
        };


        CartItemAdapter cartItemAdapter = new CartItemAdapter(getActivity().getApplicationContext(), token.getCart());
        cartItemAdapter.setOnRecyclerViewListener(onRecyclerViewListener);
        recyclerView.setAdapter(cartItemAdapter);

        cartEditBtn = (TextView) view.findViewById(R.id.cart_edit);
        cartEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartEditBtn.getText().equals("编辑")){
                    cartEditBtn.setText("完成");
                    cartItemEditAdapter.setOnRecyclerViewListener(onRecyclerViewEditListener);
                    recyclerView.setAdapter(cartItemEditAdapter);
                }else{
                    cartEditBtn.setText("编辑");
                    CartItemAdapter cartItemAdapter = new CartItemAdapter(getActivity().getApplicationContext(), token.getCart());
                    cartItemAdapter.setOnRecyclerViewListener(onRecyclerViewListener);
                    recyclerView.setAdapter(cartItemAdapter);
                }

            }
        });

        return view;
    }

}
