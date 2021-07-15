package com.triton.bertsproject.responsepojo;

import java.util.List;

public class ShowCartListResponse {



    /**
     * status : Success
     * code : 200
     * message : Cart list successful
     * data : {"cart":[{"basket_id":"16","userid":"541","basket_product_id":"2","basket_quantity":"2","basket_unit_price":"50000","basket_total_price":"100000","price":"1000","title":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","subcategory_name":"Engine Cooling","category_name":"Performance & Tuning","category_id":"6","subcategory_id":"21","part_number":"21460-8H303","vin":null,"images":[{"id":"339","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088083eaebd7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088083ed1a5e.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088083ef176b.jpg","image_order":"1","storage":"local"},{"id":"340","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088087d10ad7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088087d345fe.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088087d570b9.jpg","image_order":"2","storage":"local"}]}],"shipping_cost":0,"discount":0,"cart_total":100000,"default_address":{"id":"348","user_id":"541","name":"tstaddr1a2","phone":"1234567890","zipcode":"123456","country_id":"13","state":"257","detail_address":null,"address1":"tstaddr1","address2":"tstaddr2","city":"tstcity1","created_at":"2021-05-18 11:48:51","is_default":"1","country_name":"Australia","state_name":"Cheltenham"}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * cart : [{"basket_id":"16","userid":"541","basket_product_id":"2","basket_quantity":"2","basket_unit_price":"50000","basket_total_price":"100000","price":"1000","title":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","subcategory_name":"Engine Cooling","category_name":"Performance & Tuning","category_id":"6","subcategory_id":"21","part_number":"21460-8H303","vin":null,"images":[{"id":"339","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088083eaebd7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088083ed1a5e.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088083ef176b.jpg","image_order":"1","storage":"local"},{"id":"340","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088087d10ad7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088087d345fe.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088087d570b9.jpg","image_order":"2","storage":"local"}]}]
     * shipping_cost : 0
     * discount : 0
     * cart_total : 100000
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
        /**
         * basket_id : 16
         * userid : 541
         * basket_product_id : 2
         * basket_quantity : 2
         * basket_unit_price : 50000
         * basket_total_price : 100000
         * price : 1000
         * title : RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03
         * subcategory_name : Engine Cooling
         * category_name : Performance & Tuning
         * category_id : 6
         * subcategory_id : 21
         * part_number : 21460-8H303
         * vin : null
         * images : [{"id":"339","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088083eaebd7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088083ed1a5e.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088083ef176b.jpg","image_order":"1","storage":"local"},{"id":"340","product_id":"2","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6088087d10ad7.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6088087d345fe.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6088087d570b9.jpg","image_order":"2","storage":"local"}]
         */

        private List<CartBean> cart;

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

        public List<CartBean> getCart() {
            return cart;
        }

        public void setCart(List<CartBean> cart) {
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

        public static class CartBean {
            private String basket_id;
            private String userid;
            private String basket_product_id;
            private String basket_quantity;
            private String basket_unit_price;
            private String basket_total_price;
            private String price;
            private String title;
            private String subcategory_name;
            private String category_name;
            private String category_id;
            private String subcategory_id;
            private String part_number;
            private Object vin;
            /**
             * id : 339
             * product_id : 2
             * image_default : http://dciinternal.in/dev01/uploads/images/img_x500_6088083eaebd7.jpg
             * image_big : http://dciinternal.in/dev01/uploads/images/img_1920x_6088083ed1a5e.jpg
             * image_small : http://dciinternal.in/dev01/uploads/images/img_x300_6088083ef176b.jpg
             * image_order : 1
             * storage : local
             */

            private List<ImagesBean> images;

            public String getBasket_id() {
                return basket_id;
            }

            public void setBasket_id(String basket_id) {
                this.basket_id = basket_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getBasket_product_id() {
                return basket_product_id;
            }

            public void setBasket_product_id(String basket_product_id) {
                this.basket_product_id = basket_product_id;
            }

            public String getBasket_quantity() {
                return basket_quantity;
            }

            public void setBasket_quantity(String basket_quantity) {
                this.basket_quantity = basket_quantity;
            }

            public String getBasket_unit_price() {
                return basket_unit_price;
            }

            public void setBasket_unit_price(String basket_unit_price) {
                this.basket_unit_price = basket_unit_price;
            }

            public String getBasket_total_price() {
                return basket_total_price;
            }

            public void setBasket_total_price(String basket_total_price) {
                this.basket_total_price = basket_total_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubcategory_name() {
                return subcategory_name;
            }

            public void setSubcategory_name(String subcategory_name) {
                this.subcategory_name = subcategory_name;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getSubcategory_id() {
                return subcategory_id;
            }

            public void setSubcategory_id(String subcategory_id) {
                this.subcategory_id = subcategory_id;
            }

            public String getPart_number() {
                return part_number;
            }

            public void setPart_number(String part_number) {
                this.part_number = part_number;
            }

            public Object getVin() {
                return vin;
            }

            public void setVin(Object vin) {
                this.vin = vin;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean {
                private String id;
                private String product_id;
                private String image_default;
                private String image_big;
                private String image_small;
                private String image_order;
                private String storage;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getImage_big() {
                    return image_big;
                }

                public void setImage_big(String image_big) {
                    this.image_big = image_big;
                }

                public String getImage_small() {
                    return image_small;
                }

                public void setImage_small(String image_small) {
                    this.image_small = image_small;
                }

                public String getImage_order() {
                    return image_order;
                }

                public void setImage_order(String image_order) {
                    this.image_order = image_order;
                }

                public String getStorage() {
                    return storage;
                }

                public void setStorage(String storage) {
                    this.storage = storage;
                }
            }
        }
    }
}
