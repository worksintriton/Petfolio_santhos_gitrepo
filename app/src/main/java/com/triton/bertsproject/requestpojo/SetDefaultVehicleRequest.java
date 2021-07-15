package com.triton.bertsproject.requestpojo;

public class SetDefaultVehicleRequest {


    /**
     * AV_ID : 10
     * MODE : SETDEFAULT
     */

    private String AV_ID;
    private String MODE;

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
