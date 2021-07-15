package com.triton.bertsproject.responsepojo;

import java.util.List;

public class ForgotPasswordResponse {


    /**
     * status : Success
     * code : 200
     * message : Password Reset Email  Sent ERROR
     * data : []
     */

    private String status;
    private int code;
    private String message;
    private List<?> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
