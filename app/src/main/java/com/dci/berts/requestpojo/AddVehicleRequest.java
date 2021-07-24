package com.dci.berts.requestpojo;

public class AddVehicleRequest {


    /**
     * USER_ID : 541
     * MAKE_ID : 32
     * MODEL_ID : 788
     * YEAR : 201
     * MODE : ADD
     */

    private String USER_ID;
    private String MAKE_ID;
    private String MODEL_ID;
    private int YEAR;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

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

    public int getYEAR() {
        return YEAR;
    }

    public void setYEAR(int YEAR) {
        this.YEAR = YEAR;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
