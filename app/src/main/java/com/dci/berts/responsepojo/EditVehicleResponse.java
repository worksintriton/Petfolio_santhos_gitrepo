package com.dci.berts.responsepojo;

import java.util.List;

public class EditVehicleResponse {


    /**
     * status : Success
     * code : 200
     * message : Edit Vehicle success
     * data : {"addvehicle":[{"av_id":"10","av_user_id":"541","av_make_id":"45","av_model_id":"778","av_year":"2014","av_default":"0","make_name":"Porsche","model_name":"Cayenne"},{"av_id":"7","av_user_id":"541","av_make_id":"37","av_model_id":"776","av_year":"2015","av_default":"1","make_name":"Acura","model_name":"ILX"},{"av_id":"6","av_user_id":"541","av_make_id":"28","av_model_id":"782","av_year":"2015","av_default":"0","make_name":"Volkswagen","model_name":"Touareg"},{"av_id":"5","av_user_id":"541","av_make_id":"28","av_model_id":"782","av_year":"2015","av_default":"0","make_name":"Volkswagen","model_name":"Touareg"},{"av_id":"3","av_user_id":"541","av_make_id":"40","av_model_id":"783","av_year":"2014","av_default":"0","make_name":"Chevrolet (gm)","model_name":"Malibu"},{"av_id":"2","av_user_id":"541","av_make_id":"32","av_model_id":"788","av_year":"2015","av_default":"0","make_name":"Ford","model_name":"S-Max"}]}
     */

    private String status;
    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * av_id : 10
         * av_user_id : 541
         * av_make_id : 45
         * av_model_id : 778
         * av_year : 2014
         * av_default : 0
         * make_name : Porsche
         * model_name : Cayenne
         */

        private List<AddvehicleBean> addvehicle;

        public List<AddvehicleBean> getAddvehicle() {
            return addvehicle;
        }

        public void setAddvehicle(List<AddvehicleBean> addvehicle) {
            this.addvehicle = addvehicle;
        }

        public static class AddvehicleBean {
            private String av_id;
            private String av_user_id;
            private String av_make_id;
            private String av_model_id;
            private String av_year;
            private String av_default;
            private String make_name;
            private String model_name;

            public String getAv_id() {
                return av_id;
            }

            public void setAv_id(String av_id) {
                this.av_id = av_id;
            }

            public String getAv_user_id() {
                return av_user_id;
            }

            public void setAv_user_id(String av_user_id) {
                this.av_user_id = av_user_id;
            }

            public String getAv_make_id() {
                return av_make_id;
            }

            public void setAv_make_id(String av_make_id) {
                this.av_make_id = av_make_id;
            }

            public String getAv_model_id() {
                return av_model_id;
            }

            public void setAv_model_id(String av_model_id) {
                this.av_model_id = av_model_id;
            }

            public String getAv_year() {
                return av_year;
            }

            public void setAv_year(String av_year) {
                this.av_year = av_year;
            }

            public String getAv_default() {
                return av_default;
            }

            public void setAv_default(String av_default) {
                this.av_default = av_default;
            }

            public String getMake_name() {
                return make_name;
            }

            public void setMake_name(String make_name) {
                this.make_name = make_name;
            }

            public String getModel_name() {
                return model_name;
            }

            public void setModel_name(String model_name) {
                this.model_name = model_name;
            }
        }
    }
}
