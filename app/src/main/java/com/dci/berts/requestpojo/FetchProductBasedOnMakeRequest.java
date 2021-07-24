package com.dci.berts.requestpojo;

public class FetchProductBasedOnMakeRequest {


    /**
     * MAKE_ID : 2
     * MODEL_ID : 30
     * SEARCH_STRING : t
     * MODE : LIST
     * USER_ID : 541
     * SORTING :
     */

    private String MAKE_ID;
    private String MODEL_ID;
    private String SEARCH_STRING;
    private String MODE;
    private String USER_ID;
    private String SORTING;

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
