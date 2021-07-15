package com.triton.bertsproject.responsepojo;

import java.util.List;

public class RemoveOverallProductsResponse {


    /**
     * status : Success
     * code : 200
     * message : Empty Cart Successful
     * data : {"cart":[],"shipping_cost":0,"discount":0,"cart_total":0,"default_address":{"id":"348","user_id":"541","name":"tstaddr1a2","phone":"1234567890","zipcode":"123456","country_id":"13","state":"257","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"1","country_name":"Australia","state_name":"Cheltenham"}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * cart : []
     * shipping_cost : 0
     * discount : 0
     * cart_total : 0
     * default_address : {"id":"348","user_id":"541","name":"tstaddr1a2","phone":"1234567890","zipcode":"123456","country_id":"13","state":"257","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"1","country_name":"Australia","state_name":"Cheltenham"}
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
        private int shipping_cost;
        private int discount;
        private int cart_total;
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
        private List<?> cart;

        public int getShipping_cost() {
            return shipping_cost;
        }

        public void setShipping_cost(int shipping_cost) {
            this.shipping_cost = shipping_cost;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getCart_total() {
            return cart_total;
        }

        public void setCart_total(int cart_total) {
            this.cart_total = cart_total;
        }

        public DefaultAddressBean getDefault_address() {
            return default_address;
        }

        public void setDefault_address(DefaultAddressBean default_address) {
            this.default_address = default_address;
        }

        public List<?> getCart() {
            return cart;
        }

        public void setCart(List<?> cart) {
            this.cart = cart;
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
    }
}
