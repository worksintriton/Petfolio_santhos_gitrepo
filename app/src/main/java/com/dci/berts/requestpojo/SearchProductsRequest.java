package com.dci.berts.requestpojo;

public class SearchProductsRequest {


    /**
     * SEARCH_STRING : wheel
     * USER_ID :
     */

    private String SEARCH_STRING;
    private String USER_ID;

    public String getSEARCH_STRING() {
        return SEARCH_STRING;
    }

    public void setSEARCH_STRING(String SEARCH_STRING) {
        this.SEARCH_STRING = SEARCH_STRING;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
