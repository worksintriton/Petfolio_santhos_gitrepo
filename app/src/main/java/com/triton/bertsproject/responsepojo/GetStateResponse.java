package com.triton.bertsproject.responsepojo;

import java.util.List;

public class GetStateResponse {


    /**
     * status : Success
     * code : 200
     * message : States list successful
     * data : {"states":[{"id":"1","name":"Andaman and Nicobar Islands","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"2","name":"Andhra Pradesh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"3","name":"Arunachal Pradesh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"4","name":"Assam","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"5","name":"Bihar","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"6","name":"Chandigarh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"7","name":"Chhattisgarh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"8","name":"Dadra and Nagar Haveli","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"9","name":"Daman and Diu","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"10","name":"Delhi","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"11","name":"Goa","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"12","name":"Gujarat","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"13","name":"Haryana","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"14","name":"Himachal Pradesh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"15","name":"Jammu and Kashmir","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"16","name":"Jharkhand","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"17","name":"Karnataka","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"18","name":"Kenmore","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"19","name":"Kerala","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"20","name":"Lakshadweep","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"21","name":"Madhya Pradesh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"22","name":"Maharashtra","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"23","name":"Manipur","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"24","name":"Meghalaya","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"25","name":"Mizoram","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"26","name":"Nagaland","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"27","name":"Narora","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"28","name":"Natwar","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"29","name":"Odisha","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"30","name":"Paschim Medinipur","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"31","name":"Pondicherry","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"32","name":"Punjab","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"33","name":"Rajasthan","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"34","name":"Sikkim","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"35","name":"Tamil Nadu","country_id":"101","tax_percentage":"18","restrict_status":"1","status":"1"},{"id":"36","name":"Telangana","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"37","name":"Tripura","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"38","name":"Uttar Pradesh","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"39","name":"Uttarakhand","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"40","name":"Vaishali","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"},{"id":"41","name":"West Bengal","country_id":"101","tax_percentage":"0","restrict_status":"0","status":"0"}]}
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
         * id : 1
         * name : Andaman and Nicobar Islands
         * country_id : 101
         * tax_percentage : 0
         * restrict_status : 0
         * status : 0
         */

        private List<StatesBean> states;

        public List<StatesBean> getStates() {
            return states;
        }

        public void setStates(List<StatesBean> states) {
            this.states = states;
        }

        public static class StatesBean {
            private String id;
            private String name;
            private String country_id;
            private String tax_percentage;
            private String restrict_status;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getTax_percentage() {
                return tax_percentage;
            }

            public void setTax_percentage(String tax_percentage) {
                this.tax_percentage = tax_percentage;
            }

            public String getRestrict_status() {
                return restrict_status;
            }

            public void setRestrict_status(String restrict_status) {
                this.restrict_status = restrict_status;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
