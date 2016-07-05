package com.devapp.home;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.devapp.util.DensityUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter {

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;
    private Token token;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private List<Product> list;

    public HomeProductAdapter(final Context context, List<Product> list) {
        this.list = list;
        token = (Token) context.getApplicationContext();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_home_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        holder.position = i;
        Product product = list.get(i);
        holder.title.setText(product.getTitle());
        int intPrice = product.getPrice().setScale(2).intValue();
        int decimalPrice = Integer.parseInt(new java.text.DecimalFormat("0").format(((product.getPrice().setScale(2).doubleValue() - intPrice) * 100)));
        if(decimalPrice != 0){
            holder.price.setText(intPrice + ".");
            holder.decimalPrice.setText(decimalPrice + "");
        }else{
            holder.price.setText(intPrice + "");
        }
        holder.sales.setText(product.getSales() + "人购买");
        Uri uri = Uri.parse(product.getThumb());
        holder.image.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView title;
        public TextView price;
        public TextView decimalPrice;
        public TextView sales;
        public SimpleDraweeView image;
        public int position;

        public ProductViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            image = (SimpleDraweeView ) itemView.findViewById(R.id.pic);
            price = (TextView) itemView.findViewById(R.id.price_int);
            decimalPrice = (TextView) itemView.findViewById(R.id.price_decimal);
            Typeface typeFace =Typeface.createFromAsset(token.getAssets(),"fonts/medium.ttf");
            price.setTypeface(typeFace);
            decimalPrice.setTypeface(typeFace);
            sales = (TextView) itemView.findViewById(R.id.sales);
            int imgHeight = (int) ((token.getWindowWidth() - DensityUtil.dip2px(token, 50)) / (2 * token.getProductImageScale()));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imgHeight);
            image.setLayoutParams(layoutParams);
            rootView = itemView.findViewById(R.id.home_product_card);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
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
