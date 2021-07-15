package com.triton.bertsproject.requestpojo;

public class AddToCartRequest {


    /**
     * USER_ID : 541
     * PRODUCT_ID : 2
     * QUANTITY : 1
     * UNIT_PRICE : 50000
     * MODE : ADDTOCART
     */

    private String USER_ID;
    private String PRODUCT_ID;
    private String QUANTITY;
    private String UNIT_PRICE;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getUNIT_PRICE() {
        return UNIT_PRICE;
    }

    public void setUNIT_PRICE(String UNIT_PRICE) {
        this.UNIT_PRICE = UNIT_PRICE;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
