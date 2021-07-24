package com.dci.berts.model;

public class RetailerProductlistModel {

    String product_name, parts_no, rating, review, price;

    int produt_image;

    boolean product_status, out_of_stock;

    public RetailerProductlistModel(String product_name, String parts_no, String rating, String review, String price, int produt_image, boolean product_status, boolean out_of_stock) {
        this.product_name = product_name;
        this.parts_no = parts_no;
        this.rating = rating;
        this.review = review;
        this.price = price;
        this.produt_image = produt_image;
        this.product_status = product_status;
        this.out_of_stock = out_of_stock;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getParts_no() {
        return parts_no;
    }

    public void setParts_no(String parts_no) {
        this.parts_no = parts_no;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProdut_image() {
        return produt_image;
    }

    public void setProdut_image(int produt_image) {
        this.produt_image = produt_image;
    }

    public boolean isProduct_status() {
        return product_status;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }

    public boolean isOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(boolean out_of_stock) {
        this.out_of_stock = out_of_stock;
    }
}
