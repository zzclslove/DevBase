package com.devapp.order;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Cart;
import com.devapp.model.CartProduct;
import com.devapp.model.Product;
import com.devapp.model.ProductProp;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter {

    private Token token;
    private Cart cart;

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
        void onSelectorChanged(int position, View v);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public CartItemAdapter(Context context, Cart cart) {
        this.cart = cart;
        token = (Token) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_cart_item, null);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        CartItemViewHolder holder = (CartItemViewHolder) viewHolder;
        holder.position = i;
        CartProduct cartProduct = cart.getCartProducts().get(i);
        holder.title.setText(cartProduct.getProductName());
        List<ProductProp> props = cartProduct.getProps();
        String propsText = "";
        for (ProductProp productProp: props){
            //propsText += productProp.getPropName() + ":" + productProp.getPropValue() + " ";
        }
        holder.props.setText(propsText);
        holder.image.setAspectRatio((float) token.getProductImageScale());
        int intPrice = cartProduct.getPrice().setScale(2).intValue();
        int decimalPrice = Integer.parseInt(new java.text.DecimalFormat("0").format(((cartProduct.getPrice().setScale(2).doubleValue() - intPrice) * 100)));
        holder.prePrice.setText("￥" + cartProduct.getPrice().divide(cartProduct.getDiscount(), 2, BigDecimal.ROUND_HALF_EVEN).toString());
        if(decimalPrice != 0){
            holder.price.setText("￥" + intPrice + ".");
            holder.decimalPrice.setText(decimalPrice + "");
        }else{
            holder.price.setText("￥" + intPrice + "");
        }
        holder.productNum.setText(" X " + cartProduct.getNum());
        Uri uri = Uri.parse(cartProduct.getThumb());
        holder.image.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return cart.getCartProducts().size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public CheckBox selector;
        public TextView title;
        public TextView props;
        public SimpleDraweeView image;
        public TextView price;
        public TextView decimalPrice;
        public TextView prePrice;
        public TextView productNum;
        public int position;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            selector = (CheckBox) itemView.findViewById(R.id.cart_item_selector);
            title = (TextView) itemView.findViewById(R.id.cart_item_name);
            props = (TextView) itemView.findViewById(R.id.cart_item_prop);
            image = (SimpleDraweeView) itemView.findViewById(R.id.cart_item_pic);
            price = (TextView) itemView.findViewById(R.id.cart_item_int_price);
            decimalPrice = (TextView) itemView.findViewById(R.id.cart_item_decimal_price);
            prePrice = (TextView) itemView.findViewById(R.id.cart_item_pre_price);
            prePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            Typeface typeFace =Typeface.createFromAsset(token.getAssets(),"fonts/medium.ttf");
            price.setTypeface(typeFace);
            decimalPrice.setTypeface(typeFace);
            productNum = (TextView) itemView.findViewById(R.id.cart_item_product_num);
            rootView = itemView.findViewById(R.id.cart_item_card);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                switch (v.getId()){
                    case R.id.cart_item_card:
                        onRecyclerViewListener.onItemClick(position);
                        break;
                    case R.id.cart_item_selector:
                        onRecyclerViewListener.onSelectorChanged(position, v);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }

}
