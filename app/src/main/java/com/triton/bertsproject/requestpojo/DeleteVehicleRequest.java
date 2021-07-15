package com.triton.bertsproject.requestpojo;

public class DeleteVehicleRequest {


    /**
     * USER_ID : 541
     * AV_ID : 1
     * MODE : DELETE
     */

    private String USER_ID;
    private String AV_ID;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getAV_ID() {
        return AV_ID;
    }

    public void setAV_ID(String AV_ID) {
        this.AV_ID = AV_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
