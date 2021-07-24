package com.dci.berts.responsepojo;

import java.util.List;

public class FetchAllColorsResponse {


    /**
     * status : Success
     * code : 200
     * message : Colors list successful
     * data : {"colors":[{"id":"1","name":"Red","status":"1","created_at":"2020-09-06 23:36:52"},{"id":"2","name":"Black","status":"1","created_at":"2019-10-16 08:42:29"},{"id":"3","name":"Green","status":"1","created_at":"2019-10-16 08:42:37"},{"id":"4","name":"Silver","status":"1","created_at":"2019-10-16 08:42:49"},{"id":"5","name":"Golden","status":"1","created_at":"2019-10-16 08:42:59"},{"id":"6","name":"White","status":"1","created_at":"2020-09-03 00:20:50"},{"id":"7","name":"Grey ","status":"1","created_at":"2020-12-26 04:27:07"}]}
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
         * name : Red
         * status : 1
         * created_at : 2020-09-06 23:36:52
         */

        private List<ColorsBean> colors;

        public List<ColorsBean> getColors() {
            return colors;
        }

        public void setColors(List<ColorsBean> colors) {
            this.colors = colors;
        }

        public static class ColorsBean {
            private String id;
            private String name;
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
