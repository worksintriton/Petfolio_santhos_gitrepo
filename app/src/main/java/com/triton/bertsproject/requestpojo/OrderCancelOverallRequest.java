package com.triton.bertsproject.requestpojo;

public class OrderCancelOverallRequest {


    /**
     * MODE : CANCEL
     * USER_ID : 541
     * ORDER_ID : 303
     */

    private String MODE;
    private String USER_ID;
    private String ORDER_ID;

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

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }
}
