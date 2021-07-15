package com.triton.bertsproject.requestpojo;

public class EditAddressListRequest {


    /**
     * ADDRESS_ID : 362
     * NAME : tstaddr1a
     * PHONE : 1234567890
     * ADDRESS1 : tstaddr1a
     * ADDRESS2 : tstaddr2a
     * CITY : tstcity1a
     * COUNTRY_ID : 101
     * STATE : 20
     * ZIP_CODE : 123455
     * DEFAULT : 0
     * MODE : EDIT
     */

    private String ADDRESS_ID;
    private String NAME;
    private String PHONE;
    private String ADDRESS1;
    private String ADDRESS2;
    private String CITY;
    private String COUNTRY_ID;
    private String STATE;
    private String ZIP_CODE;
    private String DEFAULT;
    private String MODE;

    public String getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void setADDRESS_ID(String ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDRESS1() {
        return ADDRESS1;
    }

    public void setADDRESS1(String ADDRESS1) {
        this.ADDRESS1 = ADDRESS1;
    }

    public String getADDRESS2() {
        return ADDRESS2;
    }

    public void setADDRESS2(String ADDRESS2) {
        this.ADDRESS2 = ADDRESS2;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(String COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getZIP_CODE() {
        return ZIP_CODE;
    }

    public void setZIP_CODE(String ZIP_CODE) {
        this.ZIP_CODE = ZIP_CODE;
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
