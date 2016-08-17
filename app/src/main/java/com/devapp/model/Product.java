package com.devapp.model;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private static final long serialVersionUID = 4375884007642890474L;

    private int id;
    private String name;
    private String market_price;
    private String shop_price;
    private String promote_price;
    private String thumb;
    private List<ProductImage> goods_img;
    private String brief;
    private String promote_end_date;
    private String promote_start_date;
    private int cat_id;
    private int click_count;
    private String goods_desc;
    private int goods_sales;
    private Float comment_rank;
    private int comments_count;
    private int notes_count;
    private List<Comment> comments;
    private List<BuyNote> notes;
    private List<ProductProp> props;
    private List<ProductSpec> specs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(String promote_price) {
        this.promote_price = promote_price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getPromote_end_date() {
        return promote_end_date;
    }

    public void setPromote_end_date(String promote_end_date) {
        this.promote_end_date = promote_end_date;
    }

    public String getPromote_start_date() {
        return promote_start_date;
    }

    public void setPromote_start_date(String promote_start_date) {
        this.promote_start_date = promote_start_date;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public int getGoods_sales() {
        return goods_sales;
    }

    public void setGoods_sales(int goods_sales) {
        this.goods_sales = goods_sales;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<BuyNote> getNotes() {
        return notes;
    }

    public void setNotes(List<BuyNote> notes) {
        this.notes = notes;
    }

    public List<ProductProp> getProps() {
        return props;
    }

    public void setProps(List<ProductProp> props) {
        this.props = props;
    }

    public List<ProductSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<ProductSpec> specs) {
        this.specs = specs;
    }

    public Float getComment_rank() {
        return comment_rank;
    }

    public void setComment_rank(Float comment_rank) {
        this.comment_rank = comment_rank;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getNotes_count() {
        return notes_count;
    }

    public void setNotes_count(int notes_count) {
        this.notes_count = notes_count;
    }

    public List<ProductImage> getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(List<ProductImage> goods_img) {
        this.goods_img = goods_img;
    }
}
