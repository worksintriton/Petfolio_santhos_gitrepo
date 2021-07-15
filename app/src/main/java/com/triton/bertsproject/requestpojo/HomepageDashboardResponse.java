package com.triton.bertsproject.requestpojo;

public class HomepageDashboardResponse {


    /**
     * status : Success
     * code : 200
     * message : User Details
     * data : {"cart_count":1,"default_vehicle":{"av_id":"7","av_user_id":"541","av_make_id":"37","av_model_id":"776","av_year":"2015","av_default":"1","make_name":"Acura","model_name":"ILX"},"default_address":{"id":"348","user_id":"541","name":"tstaddr1a2","phone":"1234567890","zipcode":"123456","country_id":"13","state":"257","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"1","country_name":"Australia","state_name":"Cheltenham"},"profile":{"first_name":"testc","last_name":"testc","email":"prabhu.imsc@gmail.com","role":"customer"}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * cart_count : 1
     * default_vehicle : {"av_id":"7","av_user_id":"541","av_make_id":"37","av_model_id":"776","av_year":"2015","av_default":"1","make_name":"Acura","model_name":"ILX"}
     * default_address : {"id":"348","user_id":"541","name":"tstaddr1a2","phone":"1234567890","zipcode":"123456","country_id":"13","state":"257","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"1","country_name":"Australia","state_name":"Cheltenham"}
     * profile : {"first_name":"testc","last_name":"testc","email":"prabhu.imsc@gmail.com","role":"customer"}
     */

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
        private int cart_count;
        /**
         * av_id : 7
         * av_user_id : 541
         * av_make_id : 37
         * av_model_id : 776
         * av_year : 2015
         * av_default : 1
         * make_name : Acura
         * model_name : ILX
         */

        private DefaultVehicleBean default_vehicle;
        /**
         * id : 348
         * user_id : 541
         * name : tstaddr1a2
         * phone : 1234567890
         * zipcode : 123456
         * country_id : 13
         * state : 257
         * detail_address : null
         * address1 : tstaddr1
         * address2 : tstaddr2
         * city : tstcity1
         * created_at : 2021-05-18 11:48:51
         * is_default : 1
         * country_name : Australia
         * state_name : Cheltenham
         */

        private DefaultAddressBean default_address;
        /**
         * first_name : testc
         * last_name : testc
         * email : prabhu.imsc@gmail.com
         * role : customer
         */

        private ProfileBean profile;

        public int getCart_count() {
            return cart_count;
        }

        public void setCart_count(int cart_count) {
            this.cart_count = cart_count;
        }

        public DefaultVehicleBean getDefault_vehicle() {
            return default_vehicle;
        }

        public void setDefault_vehicle(DefaultVehicleBean default_vehicle) {
            this.default_vehicle = default_vehicle;
        }

        public DefaultAddressBean getDefault_address() {
            return default_address;
        }

        public void setDefault_address(DefaultAddressBean default_address) {
            this.default_address = default_address;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public static class DefaultVehicleBean {
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

        public static class DefaultAddressBean {
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

        public static class ProfileBean {
            private String first_name;
            private String last_name;
            private String email;
            private String role;

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }
    }
}
