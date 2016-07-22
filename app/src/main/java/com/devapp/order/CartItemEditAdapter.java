package com.devapp.order;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Cart;
import com.devapp.model.CartProduct;
import com.devapp.model.ProductProp;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CartItemEditAdapter extends RecyclerView.Adapter {

    private Token token;
    private Cart cart;

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
        void onButtonAddClick(int position, RecyclerView.ViewHolder v);
        void onButtonReduceClick(int position, RecyclerView.ViewHolder v);
        void onNumEditClick(int position, RecyclerView.ViewHolder v);
        void onSelectorChanged(int position, View v);
        void onDeleteBtnClick(int position);
        void onPropsEditBtnClick(int position, View v);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public CartItemEditAdapter(Context context, Cart cart) {
        this.cart = cart;
        token = (Token) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_cart_item_edit, null);
        return new CartItemEditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final CartItemEditViewHolder holder = (CartItemEditViewHolder) viewHolder;
        holder.position = i;
        CartProduct cartProduct = cart.getCartProducts().get(i);
        List<ProductProp> props = cartProduct.getProps();
        String propsText = "";
        for (ProductProp productProp: props){
            //propsText += productProp.getPropName() + ":" + productProp.getPropValue() + " ";
        }
        holder.props.setText(propsText);
        holder.image.setAspectRatio((float) token.getProductImageScale());
        holder.editNum.setText(cartProduct.getNum() + "");
        Uri uri = Uri.parse(cartProduct.getThumb());
        holder.image.setImageURI(uri);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onButtonAddClick(holder.position, holder);
            }
        });
        holder.reduceButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onButtonReduceClick(holder.position, holder);
            }
        });
        holder.editNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onNumEditClick(holder.position, holder);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onDeleteBtnClick(holder.position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cart.getCartProducts().size();
    }

    class CartItemEditViewHolder extends RecyclerView.ViewHolder
    {
        public View rootView;
        public CheckBox selector;
        public TextView props;
        public SimpleDraweeView image;
        public ImageButton addButton;
        public ImageButton reduceButon;
        public TextView editNum;
        public LinearLayout deleteBtn;
        public LinearLayout propEditBtn;
        public int position;

        public CartItemEditViewHolder(View itemView) {
            super(itemView);
            selector = (CheckBox) itemView.findViewById(R.id.cart_item_selector);
            props = (TextView) itemView.findViewById(R.id.cart_item_prop);
            image = (SimpleDraweeView) itemView.findViewById(R.id.cart_item_pic);
            addButton = (ImageButton) itemView.findViewById(R.id.cart_item_add);
            reduceButon = (ImageButton) itemView.findViewById(R.id.cart_item_reduce);
            editNum = (TextView) itemView.findViewById(R.id.cart_item_num);
            deleteBtn = (LinearLayout) itemView.findViewById(R.id.delete_btn);
            propEditBtn = (LinearLayout) itemView.findViewById(R.id.cart_item_prop_edit);
            rootView = itemView.findViewById(R.id.cart_item_card);
        }

//        @Override
//        public void onClick(View v) {
//            if (null != onRecyclerViewListener) {
//                switch (v.getId()){
//                    case R.id.cart_item_card:
//                        onRecyclerViewListener.onItemClick(position);
//                        break;
//                    case R.id.cart_item_selector:
//                        onRecyclerViewListener.onSelectorChanged(position, v);
//                        break;
//                    case R.id.delete_btn:
//                        onRecyclerViewListener.onDeleteBtnClick(position, v);
//                        break;
//                    case R.id.cart_item_prop_edit:
//                        onRecyclerViewListener.onPropsEditBtnClick(position, v);
//                }
//            }
//        }

    }

}
