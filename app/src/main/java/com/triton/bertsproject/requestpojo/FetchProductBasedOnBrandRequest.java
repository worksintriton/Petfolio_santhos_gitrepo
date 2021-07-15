package com.triton.bertsproject.requestpojo;

public class FetchProductBasedOnBrandRequest {


    /**
     * BRAND_ID : 24
     * SEARCH_STRING : t
     * MODE : LIST
     * USER_ID : 541
     * SORTING :
     */

    private String BRAND_ID;
    private String SEARCH_STRING;
    private String MODE;
    private String USER_ID;
    private String SORTING;

    public String getBRAND_ID() {
        return BRAND_ID;
    }

    public void setBRAND_ID(String BRAND_ID) {
        this.BRAND_ID = BRAND_ID;
    }

    public String getSEARCH_STRING() {
        return SEARCH_STRING;
    }

    public void setSEARCH_STRING(String SEARCH_STRING) {
        this.SEARCH_STRING = SEARCH_STRING;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getSORTING() {
        return SORTING;
    }

    public void setSORTING(String SORTING) {
        this.SORTING = SORTING;
    }
}
