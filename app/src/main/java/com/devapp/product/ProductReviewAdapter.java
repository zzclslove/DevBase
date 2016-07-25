package com.devapp.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devapp.R;
import com.devapp.model.Comment;

import org.w3c.dom.Text;

import java.util.List;

import io.techery.properratingbar.ProperRatingBar;

public class ProductReviewAdapter extends BaseAdapter {

    private Context context;
    private List<Comment> data;
    private LayoutInflater layoutInflater;

    public ProductReviewAdapter(Context context, List<Comment> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_product_review, null);
            viewHolder.ratingBar = (ProperRatingBar) convertView.findViewById(R.id.ratingBar);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.comments_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.comments_content);
            viewHolder.tvRecontent = (TextView) convertView.findViewById(R.id.comments_recontent);
            viewHolder.llRecontentContrainer = (LinearLayout) convertView.findViewById(R.id.recomments_container);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ratingBar.setRating(data.get(i).getRank());
        if(data.get(i).getUsername() == null || data.get(i).getUsername().length() == 0){
            viewHolder.tvUserName.setText("匿名用户");
        }else{
            viewHolder.tvUserName.setText(data.get(i).getUsername());
        }
        viewHolder.tvContent.setText(data.get(i).getContent());
        if(data.get(i).getRe_content() == null || data.get(i).getRe_content().length() == 0){
            viewHolder.llRecontentContrainer.setVisibility(View.GONE);
        }else{
            viewHolder.tvRecontent.setText(data.get(i).getRe_content());
        }
        return convertView;
    }

    public final class ViewHolder{
        public ProperRatingBar ratingBar;
        public TextView tvUserName;
        public TextView tvContent;
        private TextView tvRecontent;
        private LinearLayout llRecontentContrainer;
    }

}
