package com.dci.berts.responsepojo;

public class SignupResponse {



    /**
     * status : Success
     * code : 200
     * message : User registraion Successful
     * data : {"authorization":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ODAiLCJwcm9maWxlIjp7ImlkIjoiNTgwIiwiZW1haWwiOiJwcmFiaHUuaW1zYzJAZ21haWwuY29tIiwicm9sZSI6InJldGFpbCJ9fQ.OSgA0lswlxglrSCsId5nw8ZqB1q2nsHxo6gKyiZkfRU","profile":{"id":"580","username":"testab testab","slug":"testab-testab-580","email":"prabhu.imsc2@gmail.com","email_status":"1","token":"60ae298f61e804-05414647-38991233","role":"retail","balance":"0","number_of_sales":"0","user_type":"registered","twitter_id":null,"facebook_id":null,"avatar":"http://dciinternal.in/dev01/assets/img/user.png","banned":"0","about_me":null,"phone_number":null,"country_id":"0","state_id":"0","address":null,"zip_code":"0","show_email":"1","show_phone":"1","show_location":"1","facebook_url":null,"twitter_url":null,"instagram_url":null,"pinterest_url":null,"linkedin_url":null,"vk_url":null,"youtube_url":null,"last_seen":null,"show_rss_feeds":"1","send_email_new_message":"0","shipping_first_name":null,"shipping_last_name":null,"shipping_email":null,"shipping_phone_number":null,"shipping_address_1":null,"shipping_address_2":null,"shipping_country_id":null,"shipping_state":null,"shipping_city":null,"shipping_zip_code":null,"created_at":"2021-05-26 10:57:19","first_name":"testab","last_name":"testab","request_wholesale":"0","revenue":"0"}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * authorization : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ODAiLCJwcm9maWxlIjp7ImlkIjoiNTgwIiwiZW1haWwiOiJwcmFiaHUuaW1zYzJAZ21haWwuY29tIiwicm9sZSI6InJldGFpbCJ9fQ.OSgA0lswlxglrSCsId5nw8ZqB1q2nsHxo6gKyiZkfRU
     * profile : {"id":"580","username":"testab testab","slug":"testab-testab-580","email":"prabhu.imsc2@gmail.com","email_status":"1","token":"60ae298f61e804-05414647-38991233","role":"retail","balance":"0","number_of_sales":"0","user_type":"registered","twitter_id":null,"facebook_id":null,"avatar":"http://dciinternal.in/dev01/assets/img/user.png","banned":"0","about_me":null,"phone_number":null,"country_id":"0","state_id":"0","address":null,"zip_code":"0","show_email":"1","show_phone":"1","show_location":"1","facebook_url":null,"twitter_url":null,"instagram_url":null,"pinterest_url":null,"linkedin_url":null,"vk_url":null,"youtube_url":null,"last_seen":null,"show_rss_feeds":"1","send_email_new_message":"0","shipping_first_name":null,"shipping_last_name":null,"shipping_email":null,"shipping_phone_number":null,"shipping_address_1":null,"shipping_address_2":null,"shipping_country_id":null,"shipping_state":null,"shipping_city":null,"shipping_zip_code":null,"created_at":"2021-05-26 10:57:19","first_name":"testab","last_name":"testab","request_wholesale":"0","revenue":"0"}
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
        private String authorization;
        /**
         * id : 580
         * username : testab testab
         * slug : testab-testab-580
         * email : prabhu.imsc2@gmail.com
         * email_status : 1
         * token : 60ae298f61e804-05414647-38991233
         * role : retail
         * balance : 0
         * number_of_sales : 0
         * user_type : registered
         * twitter_id : null
         * facebook_id : null
         * avatar : http://dciinternal.in/dev01/assets/img/user.png
         * banned : 0
         * about_me : null
         * phone_number : null
         * country_id : 0
         * state_id : 0
         * address : null
         * zip_code : 0
         * show_email : 1
         * show_phone : 1
         * show_location : 1
         * facebook_url : null
         * twitter_url : null
         * instagram_url : null
         * pinterest_url : null
         * linkedin_url : null
         * vk_url : null
         * youtube_url : null
         * last_seen : null
         * show_rss_feeds : 1
         * send_email_new_message : 0
         * shipping_first_name : null
         * shipping_last_name : null
         * shipping_email : null
         * shipping_phone_number : null
         * shipping_address_1 : null
         * shipping_address_2 : null
         * shipping_country_id : null
         * shipping_state : null
         * shipping_city : null
         * shipping_zip_code : null
         * created_at : 2021-05-26 10:57:19
         * first_name : testab
         * last_name : testab
         * request_wholesale : 0
         * revenue : 0
         */

        private ProfileBean profile;

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public static class ProfileBean {
            private String id;
            private String username;
            private String slug;
            private String email;
            private String email_status;
            private String token;
            private String role;
            private String balance;
            private String number_of_sales;
            private String user_type;
            private Object twitter_id;
            private Object facebook_id;
            private String avatar;
            private String banned;
            private Object about_me;
            private Object phone_number;
            private String country_id;
            private String state_id;
            private Object address;
            private String zip_code;
            private String show_email;
            private String show_phone;
            private String show_location;
            private Object facebook_url;
            private Object twitter_url;
            private Object instagram_url;
            private Object pinterest_url;
            private Object linkedin_url;
            private Object vk_url;
            private Object youtube_url;
            private Object last_seen;
            private String show_rss_feeds;
            private String send_email_new_message;
            private Object shipping_first_name;
            private Object shipping_last_name;
            private Object shipping_email;
            private Object shipping_phone_number;
            private Object shipping_address_1;
            private Object shipping_address_2;
            private Object shipping_country_id;
            private Object shipping_state;
            private Object shipping_city;
            private Object shipping_zip_code;
            private String created_at;
            private String first_name;
            private String last_name;
            private String request_wholesale;
            private String revenue;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getEmail_status() {
                return email_status;
            }

            public void setEmail_status(String email_status) {
                this.email_status = email_status;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getNumber_of_sales() {
                return number_of_sales;
            }

            public void setNumber_of_sales(String number_of_sales) {
                this.number_of_sales = number_of_sales;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public Object getTwitter_id() {
                return twitter_id;
            }

            public void setTwitter_id(Object twitter_id) {
                this.twitter_id = twitter_id;
            }

            public Object getFacebook_id() {
                return facebook_id;
            }

            public void setFacebook_id(Object facebook_id) {
                this.facebook_id = facebook_id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getBanned() {
                return banned;
            }

            public void setBanned(String banned) {
                this.banned = banned;
            }

            public Object getAbout_me() {
                return about_me;
            }

            public void setAbout_me(Object about_me) {
                this.about_me = about_me;
            }

            public Object getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(Object phone_number) {
                this.phone_number = phone_number;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getState_id() {
                return state_id;
            }

            public void setState_id(String state_id) {
                this.state_id = state_id;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public String getZip_code() {
                return zip_code;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public String getShow_email() {
                return show_email;
            }

            public void setShow_email(String show_email) {
                this.show_email = show_email;
            }

            public String getShow_phone() {
                return show_phone;
            }

            public void setShow_phone(String show_phone) {
                this.show_phone = show_phone;
            }

            public String getShow_location() {
                return show_location;
            }

            public void setShow_location(String show_location) {
                this.show_location = show_location;
            }

            public Object getFacebook_url() {
                return facebook_url;
            }

            public void setFacebook_url(Object facebook_url) {
                this.facebook_url = facebook_url;
            }

            public Object getTwitter_url() {
                return twitter_url;
            }

            public void setTwitter_url(Object twitter_url) {
                this.twitter_url = twitter_url;
            }

            public Object getInstagram_url() {
                return instagram_url;
            }

            public void setInstagram_url(Object instagram_url) {
                this.instagram_url = instagram_url;
            }

            public Object getPinterest_url() {
                return pinterest_url;
            }

            public void setPinterest_url(Object pinterest_url) {
                this.pinterest_url = pinterest_url;
            }

            public Object getLinkedin_url() {
                return linkedin_url;
            }

            public void setLinkedin_url(Object linkedin_url) {
                this.linkedin_url = linkedin_url;
            }

            public Object getVk_url() {
                return vk_url;
            }

            public void setVk_url(Object vk_url) {
                this.vk_url = vk_url;
            }

            public Object getYoutube_url() {
                return youtube_url;
            }

            public void setYoutube_url(Object youtube_url) {
                this.youtube_url = youtube_url;
            }

            public Object getLast_seen() {
                return last_seen;
            }

            public void setLast_seen(Object last_seen) {
                this.last_seen = last_seen;
            }

            public String getShow_rss_feeds() {
                return show_rss_feeds;
            }

            public void setShow_rss_feeds(String show_rss_feeds) {
                this.show_rss_feeds = show_rss_feeds;
            }

            public String getSend_email_new_message() {
                return send_email_new_message;
            }

            public void setSend_email_new_message(String send_email_new_message) {
                this.send_email_new_message = send_email_new_message;
            }

            public Object getShipping_first_name() {
                return shipping_first_name;
            }

            public void setShipping_first_name(Object shipping_first_name) {
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

            public Object getShipping_phone_number() {
                return shipping_phone_number;
            }

            public void setShipping_phone_number(Object shipping_phone_number) {
                this.shipping_phone_number = shipping_phone_number;
            }

            public Object getShipping_address_1() {
                return shipping_address_1;
            }

            public void setShipping_address_1(Object shipping_address_1) {
                this.shipping_address_1 = shipping_address_1;
            }

            public Object getShipping_address_2() {
                return shipping_address_2;
            }

            public void setShipping_address_2(Object shipping_address_2) {
                this.shipping_address_2 = shipping_address_2;
            }

            public Object getShipping_country_id() {
                return shipping_country_id;
            }

            public void setShipping_country_id(Object shipping_country_id) {
                this.shipping_country_id = shipping_country_id;
            }

            public Object getShipping_state() {
                return shipping_state;
            }

            public void setShipping_state(Object shipping_state) {
                this.shipping_state = shipping_state;
            }

            public Object getShipping_city() {
                return shipping_city;
            }

            public void setShipping_city(Object shipping_city) {
                this.shipping_city = shipping_city;
            }

            public Object getShipping_zip_code() {
                return shipping_zip_code;
            }

            public void setShipping_zip_code(Object shipping_zip_code) {
                this.shipping_zip_code = shipping_zip_code;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

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

            public String getRequest_wholesale() {
                return request_wholesale;
            }

            public void setRequest_wholesale(String request_wholesale) {
                this.request_wholesale = request_wholesale;
            }

            public String getRevenue() {
                return revenue;
            }

            public void setRevenue(String revenue) {
                this.revenue = revenue;
            }
        }
    }
}
