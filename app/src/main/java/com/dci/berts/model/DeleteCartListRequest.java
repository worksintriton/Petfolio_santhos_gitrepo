package com.dci.berts.model;

public class DeleteCartListRequest {


    /**
     * USER_ID : 1
     * BASKET_ID : 8
     * MODE : DELETE
     */

    private String USER_ID;
    private String BASKET_ID;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getBASKET_ID() {
        return BASKET_ID;
    }

    public void setBASKET_ID(String BASKET_ID) {
        this.BASKET_ID = BASKET_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
