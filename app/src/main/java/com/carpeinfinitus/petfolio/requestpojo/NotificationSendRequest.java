package com.carpeinfinitus.petfolio.requestpojo;

public class NotificationSendRequest {

    /**
     * status : Payment Failed
     * date : 23-10-2020 11:00 AM
     * appointment_UID :
     * user_id : 601b8ac3204c595ee52582f2
     * doctor_id :
     */

    private String status;
    private String date;
    private String appointment_UID;
    private String user_id;
    private String doctor_id;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

    }


    public String getAppointment_UID() {
        return appointment_UID;
    }

    public void setAppointment_UID(String appointment_UID) {
        this.appointment_UID = appointment_UID;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;

    }


    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;

    }
}
