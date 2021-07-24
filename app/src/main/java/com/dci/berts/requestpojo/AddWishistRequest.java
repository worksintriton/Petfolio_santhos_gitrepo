package com.dci.berts.requestpojo;

public class AddWishistRequest {


    /**
     * USER_ID : 541
     * PRODUCT_ID : 4
     * MODE : ADD_DELETE
     */

    private String USER_ID;
    private String PRODUCT_ID;
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

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
