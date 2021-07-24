package com.dci.berts.requestpojo;

public class UpdateProfileRequest {


    /**
     * first_name : testc
     * last_name : testc
     * about_me : testc_abt
     * country_id : 101
     * state_id : 5
     * zip_code : 123456
     * revenue : 0
     * user_id : 541
     */

    private String first_name;
    private String last_name;
    private String about_me;
    private String country_id;
    private String state_id;
    private String zip_code;
    private String revenue;
    private String user_id;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
