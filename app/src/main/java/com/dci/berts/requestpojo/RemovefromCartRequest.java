package com.dci.berts.requestpojo;

public class RemovefromCartRequest {


    /**
     * USER_ID : 541
     * PRODUCT_ID : 2
     * QUANTITY : 1
     * MODE : REMOVE
     */

    private String USER_ID;
    private String PRODUCT_ID;
    private String QUANTITY;
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

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
