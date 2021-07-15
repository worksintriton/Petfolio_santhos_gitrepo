package com.triton.bertsproject.requestpojo;

public class FilterRequest {


    /**
     * CATEGORY_ID : 1
     * BRAND_ID : 1
     * MAKE_ID : 5,1,2
     * MODEL_ID : 174,226
     * YEAR : 2015,2017,2013
     * MODE : LIST
     * RATING : 3
     * COLOR : red,white
     * MIN_PRICE : 5000
     * MAX_PRICE : 15000
     * USER_ID : 541
     */

    private String CATEGORY_ID;
    private String BRAND_ID;
    private String MAKE_ID;
    private String MODEL_ID;
    private String YEAR;
    private String MODE;
    private String RATING;
    private String COLOR;
    private String MIN_PRICE;
    private String MAX_PRICE;
    private String USER_ID;

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getBRAND_ID() {
        return BRAND_ID;
    }

    public void setBRAND_ID(String BRAND_ID) {
        this.BRAND_ID = BRAND_ID;
    }

    public String getMAKE_ID() {
        return MAKE_ID;
    }

    public void setMAKE_ID(String MAKE_ID) {
        this.MAKE_ID = MAKE_ID;
    }

    public String getMODEL_ID() {
        return MODEL_ID;
    }

    public void setMODEL_ID(String MODEL_ID) {
        this.MODEL_ID = MODEL_ID;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

    public String getRATING() {
        return RATING;
    }

    public void setRATING(String RATING) {
        this.RATING = RATING;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getMIN_PRICE() {
        return MIN_PRICE;
    }

    public void setMIN_PRICE(String MIN_PRICE) {
        this.MIN_PRICE = MIN_PRICE;
    }

    public String getMAX_PRICE() {
        return MAX_PRICE;
    }

    public void setMAX_PRICE(String MAX_PRICE) {
        this.MAX_PRICE = MAX_PRICE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
