package com.triton.bertsproject.requestpojo;

public class OrderCreateRequest {


    /**
     * MODE : SAVE
     * USER_ID : 541
     * ADDRESS_ID : 351
     * PAYMENT_METHOD : Offline Payment
     */

    private String MODE;
    private String USER_ID;
    private String ADDRESS_ID;
    private String PAYMENT_METHOD;

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

    public String getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void setADDRESS_ID(String ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }

    public String getPAYMENT_METHOD() {
        return PAYMENT_METHOD;
    }

    public void setPAYMENT_METHOD(String PAYMENT_METHOD) {
        this.PAYMENT_METHOD = PAYMENT_METHOD;
    }
}
