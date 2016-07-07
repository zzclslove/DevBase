package com.devapp.findings;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.base.Token;
import com.devapp.model.Product;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FindingsItemAdapter extends RecyclerView.Adapter {

    private Token token;

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
        void onButtonClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private List<Product> list;

    public FindingsItemAdapter(Context context, List<Product> list) {
        this.list = list;
        token = (Token) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_findings_item, null);
        return new FindingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final FindingItemViewHolder holder = (FindingItemViewHolder) viewHolder;
        holder.position = i;
        Product product = list.get(i);
        holder.title.setText(product.getName());
        if(product.getBrief().length() == 0){
            holder.description.setVisibility(View.GONE);
        }else{
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(product.getBrief());
        }
        holder.endTime.setText(product.getPromote_end_date());
        holder.image.setAspectRatio((float) token.getProductImageScale());
        BigDecimal price = new BigDecimal(product.getPromote_price());
        int intPrice = price.setScale(2).intValue();
        int decimalPrice = Integer.parseInt(new java.text.DecimalFormat("0").format(((price.setScale(2).doubleValue() - intPrice) * 100)));
        if(decimalPrice != 0){
            holder.price.setText(intPrice + ".");
            holder.decimalPrice.setText(decimalPrice + "");
        }else{
            holder.price.setText(intPrice + "");
        }
        if(product.getMarket_price().length() > 0){
            holder.prePrice.setText("￥" + product.getMarket_price());
        }else{
            holder.prePrice.setText("￥" + product.getShop_price());
        }
        Uri uri = Uri.parse(token.getRootUrl() + product.getThumb());
        holder.image.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FindingItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView title;
        public TextView description;
        public SimpleDraweeView image;
        public TextView price;
        public TextView decimalPrice;
        public TextView prePrice;
        public TextView endTime;

        public int position;

        public FindingItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.findings_item_title);
            description = (TextView) itemView.findViewById(R.id.finding_item_des);
            image = (SimpleDraweeView) itemView.findViewById(R.id.findings_item_pic);
            price = (TextView) itemView.findViewById(R.id.finding_item_price_int);
            decimalPrice = (TextView) itemView.findViewById(R.id.finding_item_price_decimal);
            Typeface typeFace =Typeface.createFromAsset(token.getAssets(),"fonts/medium.ttf");
            price.setTypeface(typeFace);
            decimalPrice.setTypeface(typeFace);
            prePrice = (TextView) itemView.findViewById(R.id.finding_item_pre_price);
            prePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            endTime = (TextView) itemView.findViewById(R.id.end_time);
            rootView = itemView.findViewById(R.id.findings_item_root);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                switch (v.getId()){
                    case R.id.finding_item:
                        onRecyclerViewListener.onItemClick(position);
                        break;
                    case R.id.finding_item_button:
                        onRecyclerViewListener.onButtonClick(position);
                        break;
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
