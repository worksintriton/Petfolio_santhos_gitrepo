package com.triton.bertsproject.responsepojo;

import java.util.List;

public class FetchAllBrandsResponse {

    /**
     * status : true
     * error_code : 0
     * error_message :
     * data : {"display_message":"","brands":[{"id":"1","name":"BAK","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein.png","status":"1","created_at":"2019-10-16 15:42:21"},{"id":"2","name":"Covercraft","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-1.png","status":"1","created_at":"2019-10-16 15:42:29"},{"id":"3","name":"Coverking","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-2.png","status":"1","created_at":"2019-10-16 15:42:37"},{"id":"4","name":"Curt Manufacturing","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-3.png","status":"1","created_at":"2019-10-16 15:42:49"},{"id":"5","name":"EBC Brakes","logo":"http://dciinternal.in/dev01/assets/img/brands/garrett3.png","status":"1","created_at":"2019-10-16 15:42:59"},{"id":"6","name":"Husky Liners","logo":"http://dciinternal.in/dev01/assets/img/brands/MD.png","status":"1","created_at":"2019-10-16 15:43:07"},{"id":"15","name":"TVS","logo":"http://dciinternal.in/dev01/assets/img/brands/TVS-Motor.png","status":"1","created_at":"2020-08-25 05:51:37"},{"id":"16","name":"Yamaha Bike","logo":"http://dciinternal.in/dev01/assets/img/brands/Yamaha.png","status":"1","created_at":"2020-08-25 04:36:08"},{"id":"18","name":"SMA TATA","logo":"http://dciinternal.in/dev01/assets/img/brands/tata.png","status":"1","created_at":"2020-08-26 03:29:46"},{"id":"19","name":"Bajaj Chetak","logo":"http://dciinternal.in/dev01/assets/img/brands/Bajaj.jpg","status":"1","created_at":"2020-09-02 00:07:17"},{"id":"21","name":"BMW","logo":"http://dciinternal.in/dev01/assets/img/brands/BMW.png","status":"1","created_at":"2020-12-26 04:26:23"},{"id":"23","name":"Tesla","logo":null,"status":"1","created_at":"2021-04-16 06:23:30"}]}
     */

    private boolean status;
    private int error_code;
    private String error_message;
    /**
     * display_message :
     * brands : [{"id":"1","name":"BAK","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein.png","status":"1","created_at":"2019-10-16 15:42:21"},{"id":"2","name":"Covercraft","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-1.png","status":"1","created_at":"2019-10-16 15:42:29"},{"id":"3","name":"Coverking","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-2.png","status":"1","created_at":"2019-10-16 15:42:37"},{"id":"4","name":"Curt Manufacturing","logo":"http://dciinternal.in/dev01/assets/img/brands/bilstein-3.png","status":"1","created_at":"2019-10-16 15:42:49"},{"id":"5","name":"EBC Brakes","logo":"http://dciinternal.in/dev01/assets/img/brands/garrett3.png","status":"1","created_at":"2019-10-16 15:42:59"},{"id":"6","name":"Husky Liners","logo":"http://dciinternal.in/dev01/assets/img/brands/MD.png","status":"1","created_at":"2019-10-16 15:43:07"},{"id":"15","name":"TVS","logo":"http://dciinternal.in/dev01/assets/img/brands/TVS-Motor.png","status":"1","created_at":"2020-08-25 05:51:37"},{"id":"16","name":"Yamaha Bike","logo":"http://dciinternal.in/dev01/assets/img/brands/Yamaha.png","status":"1","created_at":"2020-08-25 04:36:08"},{"id":"18","name":"SMA TATA","logo":"http://dciinternal.in/dev01/assets/img/brands/tata.png","status":"1","created_at":"2020-08-26 03:29:46"},{"id":"19","name":"Bajaj Chetak","logo":"http://dciinternal.in/dev01/assets/img/brands/Bajaj.jpg","status":"1","created_at":"2020-09-02 00:07:17"},{"id":"21","name":"BMW","logo":"http://dciinternal.in/dev01/assets/img/brands/BMW.png","status":"1","created_at":"2020-12-26 04:26:23"},{"id":"23","name":"Tesla","logo":null,"status":"1","created_at":"2021-04-16 06:23:30"}]
     */

    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String display_message;
        /**
         * id : 1
         * name : BAK
         * logo : http://dciinternal.in/dev01/assets/img/brands/bilstein.png
         * status : 1
         * created_at : 2019-10-16 15:42:21
         */

        private List<BrandsBean> brands;

        public String getDisplay_message() {
            return display_message;
        }

        public void setDisplay_message(String display_message) {
            this.display_message = display_message;
        }

        public List<BrandsBean> getBrands() {
            return brands;
        }

        public void setBrands(List<BrandsBean> brands) {
            this.brands = brands;
        }

        public static class BrandsBean {
            private String id;
            private String name;
            private String logo;
            private String status;
            private String created_at;

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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
