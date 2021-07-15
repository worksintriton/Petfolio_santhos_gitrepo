package com.triton.bertsproject.model;

public class OrderlistModel {

    String product_name, parts_no, order_status, order_date, total_price, Order_ID;

    int produt_image;

    public OrderlistModel(String product_name, String parts_no, String order_status, String order_date, String total_price, String order_ID, int produt_image) {
        this.product_name = product_name;
        this.parts_no = parts_no;
        this.order_status = order_status;
        this.order_date = order_date;
        this.total_price = total_price;
        Order_ID = order_ID;
        this.produt_image = produt_image;
    }

    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String order_ID) {
        Order_ID = order_ID;
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

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public int getProdut_image() {
        return produt_image;
    }

    public void setProdut_image(int produt_image) {
        this.produt_image = produt_image;
    }
}
