package com.carpeinfinitus.petfolio.responsepojo;

public class PetAddImageResponse {

    /**
     * Status : Success
     * Message : pet details Updated
     * Data : {"_id":"5fce1ae2928d5f5634501b2d","user_id":"5fb36ca169f71e30a0ffd3f7","pet_img":"","pet_name":"POP","pet_type":"Dog","pet_breed":"breed 1","pet_gender":"Male","pet_color":"white","pet_weight":120,"pet_age":20,"vaccinated":true,"last_vaccination_date":"23-10-1996","default_status":true,"date_and_time":"23-10-1996 12:09 AM","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    private DataBean Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }


    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
