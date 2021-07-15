package com.triton.bertsproject.requestpojo;

public class FetchProductBasedOnCatRequest {


    /**
     * CATEGORY_ID : 2
     * SUBCATEGORY_ID : 30
     * SEARCH_STRING : t
     * MODE : LIST
     * USER_ID : 541
     * SORTING :
     */

    private String CATEGORY_ID;
    private String SUBCATEGORY_ID;
    private String SEARCH_STRING;
    private String MODE;
    private String USER_ID;
    private String SORTING;

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getSUBCATEGORY_ID() {
        return SUBCATEGORY_ID;
    }

    public void setSUBCATEGORY_ID(String SUBCATEGORY_ID) {
        this.SUBCATEGORY_ID = SUBCATEGORY_ID;
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
