package com.triton.bertsproject.responsepojo;

import java.util.List;

public class OrderCreateResponse {


    /**
     * status : Success
     * code : 200
     * message : Order Saved Successful
     * data : {"order":{"id":"302","order_number":"10302","buyer_id":"541","buyer_type":"registered","price_subtotal":"40000","price_shipping":"0","price_total":"40000","price_currency":"USD","shipping_first_name":"tstaddr1a","shipping_last_name":null,"shipping_email":null,"shipping_phone_number":"1234567890","shipping_address_1":"tstaddr1","shipping_address_2":"tstaddr2","shipping_country":"101","shipping_state":"19","shipping_city":"tstcity1","shipping_statename":null,"shipping_zip_code":"123456","billing_first_name":null,"billing_last_name":null,"billing_email":null,"billing_phone_number":null,"billing_address_1":"","billing_address_2":"","billing_country":null,"billing_state":null,"billing_city":null,"billing_zip_code":null,"status":"Pending","payment_method":"Offline Payment","payment_status":"awaiting_payment","updated_at":"2021-05-26 18:58:41","created_at":"2021-05-26 18:58:41","parent_order_id":"0","message_order":"","reason":null,"price_exchange_rate":"1","coupon":null,"order_type":"APP","products":[{"order_id":"302","product_id":"4","product_title":"Engine Piston","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"1","subcategory_id":"30","category_name":"Performance & Tuning","subcategory_name":"Interior Car Accessories","images":[{"id":"344","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg","image_order":"1","storage":"local"},{"id":"345","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c026f2f630.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c026f4b440.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c026f67bf5.jpg","image_order":"2","storage":"local"},{"id":"346","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c02771cb3c.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02773d836.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c02775e7d0.jpg","image_order":"3","storage":"local"}]},{"order_id":"302","product_id":"3","product_title":"Alloy Wheel ","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"32","subcategory_id":"91","category_name":"Transmission & Drivetrain","subcategory_name":"Exteriors","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]}]}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * order : {"id":"302","order_number":"10302","buyer_id":"541","buyer_type":"registered","price_subtotal":"40000","price_shipping":"0","price_total":"40000","price_currency":"USD","shipping_first_name":"tstaddr1a","shipping_last_name":null,"shipping_email":null,"shipping_phone_number":"1234567890","shipping_address_1":"tstaddr1","shipping_address_2":"tstaddr2","shipping_country":"101","shipping_state":"19","shipping_city":"tstcity1","shipping_statename":null,"shipping_zip_code":"123456","billing_first_name":null,"billing_last_name":null,"billing_email":null,"billing_phone_number":null,"billing_address_1":"","billing_address_2":"","billing_country":null,"billing_state":null,"billing_city":null,"billing_zip_code":null,"status":"Pending","payment_method":"Offline Payment","payment_status":"awaiting_payment","updated_at":"2021-05-26 18:58:41","created_at":"2021-05-26 18:58:41","parent_order_id":"0","message_order":"","reason":null,"price_exchange_rate":"1","coupon":null,"order_type":"APP","products":[{"order_id":"302","product_id":"4","product_title":"Engine Piston","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"1","subcategory_id":"30","category_name":"Performance & Tuning","subcategory_name":"Interior Car Accessories","images":[{"id":"344","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg","image_order":"1","storage":"local"},{"id":"345","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c026f2f630.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c026f4b440.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c026f67bf5.jpg","image_order":"2","storage":"local"},{"id":"346","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c02771cb3c.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02773d836.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c02775e7d0.jpg","image_order":"3","storage":"local"}]},{"order_id":"302","product_id":"3","product_title":"Alloy Wheel ","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"32","subcategory_id":"91","category_name":"Transmission & Drivetrain","subcategory_name":"Exteriors","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]}]}
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
        /**
         * id : 302
         * order_number : 10302
         * buyer_id : 541
         * buyer_type : registered
         * price_subtotal : 40000
         * price_shipping : 0
         * price_total : 40000
         * price_currency : USD
         * shipping_first_name : tstaddr1a
         * shipping_last_name : null
         * shipping_email : null
         * shipping_phone_number : 1234567890
         * shipping_address_1 : tstaddr1
         * shipping_address_2 : tstaddr2
         * shipping_country : 101
         * shipping_state : 19
         * shipping_city : tstcity1
         * shipping_statename : null
         * shipping_zip_code : 123456
         * billing_first_name : null
         * billing_last_name : null
         * billing_email : null
         * billing_phone_number : null
         * billing_address_1 :
         * billing_address_2 :
         * billing_country : null
         * billing_state : null
         * billing_city : null
         * billing_zip_code : null
         * status : Pending
         * payment_method : Offline Payment
         * payment_status : awaiting_payment
         * updated_at : 2021-05-26 18:58:41
         * created_at : 2021-05-26 18:58:41
         * parent_order_id : 0
         * message_order :
         * reason : null
         * price_exchange_rate : 1
         * coupon : null
         * order_type : APP
         * products : [{"order_id":"302","product_id":"4","product_title":"Engine Piston","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"1","subcategory_id":"30","category_name":"Performance & Tuning","subcategory_name":"Interior Car Accessories","images":[{"id":"344","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg","image_order":"1","storage":"local"},{"id":"345","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c026f2f630.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c026f4b440.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c026f67bf5.jpg","image_order":"2","storage":"local"},{"id":"346","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c02771cb3c.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02773d836.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c02775e7d0.jpg","image_order":"3","storage":"local"}]},{"order_id":"302","product_id":"3","product_title":"Alloy Wheel ","product_unit_price":"10000","product_quantity":"2","product_currency":"USD","product_total_price":"20000","category_id":"32","subcategory_id":"91","category_name":"Transmission & Drivetrain","subcategory_name":"Exteriors","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]}]
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            private String id;
            private String order_number;
            private String buyer_id;
            private String buyer_type;
            private String price_subtotal;
            private String price_shipping;
            private String price_total;
            private String price_currency;
            private String shipping_first_name;
            private Object shipping_last_name;
            private Object shipping_email;
            private String shipping_phone_number;
            private String shipping_address_1;
            private String shipping_address_2;
            private String shipping_country;
            private String shipping_state;
            private String shipping_city;
            private Object shipping_statename;
            private String shipping_zip_code;
            private Object billing_first_name;
            private Object billing_last_name;
            private Object billing_email;
            private Object billing_phone_number;
            private String billing_address_1;
            private String billing_address_2;
            private Object billing_country;
            private Object billing_state;
            private Object billing_city;
            private Object billing_zip_code;
            private String status;
            private String payment_method;
            private String payment_status;
            private String updated_at;
            private String created_at;
            private String parent_order_id;
            private String message_order;
            private Object reason;
            private String price_exchange_rate;
            private Object coupon;
            private String order_type;
            /**
             * order_id : 302
             * product_id : 4
             * product_title : Engine Piston
             * product_unit_price : 10000
             * product_quantity : 2
             * product_currency : USD
             * product_total_price : 20000
             * category_id : 1
             * subcategory_id : 30
             * category_name : Performance & Tuning
             * subcategory_name : Interior Car Accessories
             * images : [{"id":"344","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg","image_order":"1","storage":"local"},{"id":"345","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c026f2f630.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c026f4b440.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c026f67bf5.jpg","image_order":"2","storage":"local"},{"id":"346","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c02771cb3c.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02773d836.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c02775e7d0.jpg","image_order":"3","storage":"local"}]
             */

            private List<ProductsBean> products;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getBuyer_type() {
                return buyer_type;
            }

            public void setBuyer_type(String buyer_type) {
                this.buyer_type = buyer_type;
            }

            public String getPrice_subtotal() {
                return price_subtotal;
            }

            public void setPrice_subtotal(String price_subtotal) {
                this.price_subtotal = price_subtotal;
            }

            public String getPrice_shipping() {
                return price_shipping;
            }

            public void setPrice_shipping(String price_shipping) {
                this.price_shipping = price_shipping;
            }

            public String getPrice_total() {
                return price_total;
            }

            public void setPrice_total(String price_total) {
                this.price_total = price_total;
            }

            public String getPrice_currency() {
                return price_currency;
            }

            public void setPrice_currency(String price_currency) {
                this.price_currency = price_currency;
            }

            public String getShipping_first_name() {
                return shipping_first_name;
            }

            public void setShipping_first_name(String shipping_first_name) {
                this.shipping_first_name = shipping_first_name;
            }

            public Object getShipping_last_name() {
                return shipping_last_name;
            }

            public void setShipping_last_name(Object shipping_last_name) {
                this.shipping_last_name = shipping_last_name;
            }

            public Object getShipping_email() {
                return shipping_email;
            }

            public void setShipping_email(Object shipping_email) {
                this.shipping_email = shipping_email;
            }

            public String getShipping_phone_number() {
                return shipping_phone_number;
            }

            public void setShipping_phone_number(String shipping_phone_number) {
                this.shipping_phone_number = shipping_phone_number;
            }

            public String getShipping_address_1() {
                return shipping_address_1;
            }

            public void setShipping_address_1(String shipping_address_1) {
                this.shipping_address_1 = shipping_address_1;
            }

            public String getShipping_address_2() {
                return shipping_address_2;
            }

            public void setShipping_address_2(String shipping_address_2) {
                this.shipping_address_2 = shipping_address_2;
            }

            public String getShipping_country() {
                return shipping_country;
            }

            public void setShipping_country(String shipping_country) {
                this.shipping_country = shipping_country;
            }

            public String getShipping_state() {
                return shipping_state;
            }

            public void setShipping_state(String shipping_state) {
                this.shipping_state = shipping_state;
            }

            public String getShipping_city() {
                return shipping_city;
            }

            public void setShipping_city(String shipping_city) {
                this.shipping_city = shipping_city;
            }

            public Object getShipping_statename() {
                return shipping_statename;
            }

            public void setShipping_statename(Object shipping_statename) {
                this.shipping_statename = shipping_statename;
            }

            public String getShipping_zip_code() {
                return shipping_zip_code;
            }

            public void setShipping_zip_code(String shipping_zip_code) {
                this.shipping_zip_code = shipping_zip_code;
            }

            public Object getBilling_first_name() {
                return billing_first_name;
            }

            public void setBilling_first_name(Object billing_first_name) {
                this.billing_first_name = billing_first_name;
            }

            public Object getBilling_last_name() {
                return billing_last_name;
            }

            public void setBilling_last_name(Object billing_last_name) {
                this.billing_last_name = billing_last_name;
            }

            public Object getBilling_email() {
                return billing_email;
            }

            public void setBilling_email(Object billing_email) {
                this.billing_email = billing_email;
            }

            public Object getBilling_phone_number() {
                return billing_phone_number;
            }

            public void setBilling_phone_number(Object billing_phone_number) {
                this.billing_phone_number = billing_phone_number;
            }

            public String getBilling_address_1() {
                return billing_address_1;
            }

            public void setBilling_address_1(String billing_address_1) {
                this.billing_address_1 = billing_address_1;
            }

            public String getBilling_address_2() {
                return billing_address_2;
            }

            public void setBilling_address_2(String billing_address_2) {
                this.billing_address_2 = billing_address_2;
            }

            public Object getBilling_country() {
                return billing_country;
            }

            public void setBilling_country(Object billing_country) {
                this.billing_country = billing_country;
            }

            public Object getBilling_state() {
                return billing_state;
            }

            public void setBilling_state(Object billing_state) {
                this.billing_state = billing_state;
            }

            public Object getBilling_city() {
                return billing_city;
            }

            public void setBilling_city(Object billing_city) {
                this.billing_city = billing_city;
            }

            public Object getBilling_zip_code() {
                return billing_zip_code;
            }

            public void setBilling_zip_code(Object billing_zip_code) {
                this.billing_zip_code = billing_zip_code;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPayment_method() {
                return payment_method;
            }

            public void setPayment_method(String payment_method) {
                this.payment_method = payment_method;
            }

            public String getPayment_status() {
                return payment_status;
            }

            public void setPayment_status(String payment_status) {
                this.payment_status = payment_status;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getParent_order_id() {
                return parent_order_id;
            }

            public void setParent_order_id(String parent_order_id) {
                this.parent_order_id = parent_order_id;
            }

            public String getMessage_order() {
                return message_order;
            }

            public void setMessage_order(String message_order) {
                this.message_order = message_order;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public String getPrice_exchange_rate() {
                return price_exchange_rate;
            }

            public void setPrice_exchange_rate(String price_exchange_rate) {
                this.price_exchange_rate = price_exchange_rate;
            }

            public Object getCoupon() {
                return coupon;
            }

            public void setCoupon(Object coupon) {
                this.coupon = coupon;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
                private String order_id;
                private String product_id;
                private String product_title;
                private String product_unit_price;
                private String product_quantity;
                private String product_currency;
                private String product_total_price;
                private String category_id;
                private String subcategory_id;
                private String category_name;
                private String subcategory_name;
                /**
                 * id : 344
                 * product_id : 4
                 * image_default : http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg
                 * image_big : http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg
                 * image_small : http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg
                 * image_order : 1
                 * storage : local
                 */

                private List<ImagesBean> images;

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getProduct_title() {
                    return product_title;
                }

                public void setProduct_title(String product_title) {
                    this.product_title = product_title;
                }

                public String getProduct_unit_price() {
                    return product_unit_price;
                }

                public void setProduct_unit_price(String product_unit_price) {
                    this.product_unit_price = product_unit_price;
                }

                public String getProduct_quantity() {
                    return product_quantity;
                }

                public void setProduct_quantity(String product_quantity) {
                    this.product_quantity = product_quantity;
                }

                public String getProduct_currency() {
                    return product_currency;
                }

                public void setProduct_currency(String product_currency) {
                    this.product_currency = product_currency;
                }

                public String getProduct_total_price() {
                    return product_total_price;
                }

                public void setProduct_total_price(String product_total_price) {
                    this.product_total_price = product_total_price;
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

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getSubcategory_name() {
                    return subcategory_name;
                }

                public void setSubcategory_name(String subcategory_name) {
                    this.subcategory_name = subcategory_name;
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
}
