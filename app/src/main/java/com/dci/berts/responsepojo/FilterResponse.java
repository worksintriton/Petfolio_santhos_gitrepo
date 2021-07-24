package com.dci.berts.responsepojo;

import java.util.List;

public class FilterResponse {


    /**
     * status : Success
     * code : 200
     * message : No Products Found
     * data : {"products":[]}
     */

    private String status;
    private int code;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<?> products;

        public List<?> getProducts() {
            return products;
        }

        public void setProducts(List<?> products) {
            this.products = products;
        }
    }
}
