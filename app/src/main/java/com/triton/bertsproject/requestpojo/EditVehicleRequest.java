package com.triton.bertsproject.requestpojo;

public class EditVehicleRequest {

    /**
     * AV_ID : 10
     * MAKE_ID : 45
     * MODEL_ID : 778
     * YEAR : 2014
     * DEFAULT : 0
     * MODE : EDIT
     */

    private String AV_ID;
    private String MAKE_ID;
    private String MODEL_ID;
    private int YEAR;
    private String DEFAULT;
    private String MODE;

    public String getAV_ID() {
        return AV_ID;
    }

    public void setAV_ID(String AV_ID) {
        this.AV_ID = AV_ID;
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

    public String getDEFAULT() {
        return DEFAULT;
    }

    public void setDEFAULT(String DEFAULT) {
        this.DEFAULT = DEFAULT;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
