package com.dci.berts.responsepojo;

import java.util.List;

public class UserAddressListResponse {


    /**
     * status : Success
     * code : 200
     * message : Address list successful
     * data : {"address":[{"id":"352","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"101","state":"19","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-26 06:58:13","is_default":"1","country_name":"India","state_name":"Kerala"},{"id":"351","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"101","state":"19","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-25 04:42:21","is_default":"0","country_name":"India","state_name":"Kerala"},{"id":"348","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"0","country_name":"Australia","state_name":""},{"id":"346","user_id":"541","name":"tstaddr1","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-17 13:06:26","is_default":"0","country_name":"Australia","state_name":""},{"id":"345","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-17 11:59:19","is_default":"0","country_name":"Australia","state_name":""},{"id":"344","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-17 11:58:53","is_default":"0","country_name":"Australia","state_name":""},{"id":"343","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-17 11:58:19","is_default":"0","country_name":"Australia","state_name":""},{"id":"342","user_id":"541","name":"tstaddr1a","phone":"1234567890","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-17 11:54:38","is_default":"0","country_name":"Australia","state_name":""},{"id":"338","user_id":"541","name":"tstaddr2","phone":"1234567898","zipcode":"123456","country_id":"13","state":null,"detail_address":null,"address1":"tstaddr2","address2":"tstaddr2","city":"tstcity2","created_at":"2021-05-11 11:38:14","is_default":"0","country_name":"Australia","state_name":""}]}
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
         * id : 352
         * user_id : 541
         * name : tstaddr1a
         * phone : 1234567890
         * zipcode : 123456
         * country_id : 101
         * state : 19
         * detail_address : null
         * address1 : tstaddr1
         * address2 : tstaddr2
         * city : tstcity1
         * created_at : 2021-05-26 06:58:13
         * is_default : 1
         * country_name : India
         * state_name : Kerala
         */

        private List<AddressBean> address;

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean {
            private String id;
            private String user_id;
            private String name;
            private String phone;
            private String zipcode;
            private String country_id;
            private String state;
            private Object detail_address;
            private String address1;
            private String address2;
            private String city;
            private String created_at;
            private String is_default;
            private String country_name;
            private String state_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getDetail_address() {
                return detail_address;
            }

            public void setDetail_address(Object detail_address) {
                this.detail_address = detail_address;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            public String getState_name() {
                return state_name;
            }

            public void setState_name(String state_name) {
                this.state_name = state_name;
            }
        }
    }
}
