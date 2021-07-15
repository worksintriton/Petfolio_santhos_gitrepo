package com.triton.bertsproject.requestpojo;

public class RemoveWishistRequest {


    /**
     * USER_ID : 541
     * WISHLIST_ID : 3
     * MODE : DELETE
     */

    private String USER_ID;
    private String WISHLIST_ID;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getWISHLIST_ID() {
        return WISHLIST_ID;
    }

    public void setWISHLIST_ID(String WISHLIST_ID) {
        this.WISHLIST_ID = WISHLIST_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
