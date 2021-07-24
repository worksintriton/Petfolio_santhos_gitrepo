package com.dci.berts.responsepojo;

import java.util.List;

public class FetchChildMakeslistRequestResponse {


    /**
     * status : Success
     * code : 200
     * message : Models list successful
     * data : {"model":[{"id":"49","make_id":"1","name":"Vezel","status":"1","created_at":"2019-10-10 01:08:04","make_name":"Honda"},{"id":"48","make_id":"1","name":"ODYSSEY","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"47","make_id":"1","name":"LEGEND IV","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"46","make_id":"1","name":"ACCORD VIII","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"45","make_id":"1","name":"Civic (USA)","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"44","make_id":"1","name":"Crosstour","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"43","make_id":"1","name":"Civic (USA)","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"42","make_id":"1","name":"CR-V IV","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"41","make_id":"1","name":"Civic X","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"40","make_id":"1","name":"Civic VIII Saloon","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"39","make_id":"1","name":"Jade","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"38","make_id":"1","name":"Legend","status":"1","created_at":"2019-10-10 01:07:54","make_name":"Honda"},{"id":"37","make_id":"1","name":"Jazz II","status":"1","created_at":"2019-10-10 01:07:48","make_name":"Honda"},{"id":"36","make_id":"1","name":"CRX","status":"1","created_at":"2019-10-10 01:07:37","make_name":"Honda"},{"id":"35","make_id":"1","name":"Concerto","status":"1","created_at":"2019-10-10 01:07:33","make_name":"Honda"},{"id":"34","make_id":"1","name":"Mobilio","status":"1","created_at":"2019-10-10 01:07:24","make_name":"Honda"},{"id":"33","make_id":"1","name":"Fit Aria","status":"1","created_at":"2019-10-10 01:07:18","make_name":"Honda"},{"id":"32","make_id":"1","name":"Acty","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"31","make_id":"1","name":"Shuttle","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"30","make_id":"1","name":"Quint Integra","status":"1","created_at":"2019-10-10 01:07:01","make_name":"Honda"},{"id":"29","make_id":"1","name":"Pilot","status":"1","created_at":"2019-10-10 01:06:55","make_name":"Honda"},{"id":"28","make_id":"1","name":"Elysion","status":"1","created_at":"2019-10-10 01:07:54","make_name":"Honda"},{"id":"27","make_id":"1","name":"Avancier","status":"1","created_at":"2019-10-10 01:07:48","make_name":"Honda"},{"id":"26","make_id":"1","name":"Accord Coupe","status":"1","created_at":"2019-10-10 01:07:37","make_name":"Honda"},{"id":"25","make_id":"1","name":"Accord Wagon","status":"1","created_at":"2019-10-10 01:07:33","make_name":"Honda"},{"id":"24","make_id":"1","name":"Partner Van","status":"1","created_at":"2019-10-10 01:07:24","make_name":"Honda"},{"id":"23","make_id":"1","name":"Orthia","status":"1","created_at":"2019-10-10 01:07:18","make_name":"Honda"},{"id":"22","make_id":"1","name":"Integra","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"21","make_id":"1","name":"Domani","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"20","make_id":"1","name":"Civic Ferio","status":"1","created_at":"2019-10-10 01:07:01","make_name":"Honda"},{"id":"19","make_id":"1","name":"Civic Coupe","status":"1","created_at":"2019-10-10 01:06:55","make_name":"Honda"},{"id":"18","make_id":"1","name":"Torneo","status":"1","created_at":"2019-10-10 01:07:54","make_name":"Honda"},{"id":"17","make_id":"1","name":"Stream","status":"1","created_at":"2019-10-10 01:07:48","make_name":"Honda"},{"id":"16","make_id":"1","name":"Step Wagon","status":"1","created_at":"2019-10-10 01:07:37","make_name":"Honda"},{"id":"15","make_id":"1","name":"Prelude","status":"1","created_at":"2019-10-10 01:07:33","make_name":"Honda"},{"id":"14","make_id":"1","name":"Partner","status":"1","created_at":"2019-10-10 01:07:24","make_name":"Honda"},{"id":"13","make_id":"1","name":"Odyssey","status":"1","created_at":"2019-10-10 01:07:18","make_name":"Honda"},{"id":"12","make_id":"1","name":"Logo","status":"1","created_at":"2019-10-10 01:07:13","make_name":"Honda"},{"id":"11","make_id":"1","name":"Jazz","status":"1","created_at":"2019-10-10 01:07:08","make_name":"Honda"},{"id":"10","make_id":"1","name":"Inspire","status":"1","created_at":"2019-10-10 01:07:01","make_name":"Honda"},{"id":"9","make_id":"1","name":"Insight","status":"1","created_at":"2019-10-10 01:06:55","make_name":"Honda"},{"id":"8","make_id":"1","name":"HR-V","status":"1","created_at":"2019-10-10 01:06:49","make_name":"Honda"},{"id":"7","make_id":"1","name":"Hornet","status":"1","created_at":"2019-10-10 01:06:44","make_name":"Honda"},{"id":"6","make_id":"1","name":"Fit","status":"1","created_at":"2019-10-10 01:06:37","make_name":"Honda"},{"id":"5","make_id":"1","name":"CR-V","status":"1","created_at":"2019-10-10 01:06:30","make_name":"Honda"},{"id":"4","make_id":"1","name":"Civic","status":"1","created_at":"2019-10-10 01:06:21","make_name":"Honda"},{"id":"3","make_id":"1","name":"City","status":"1","created_at":"2019-10-10 01:06:16","make_name":"Honda"},{"id":"2","make_id":"1","name":"Air Wave","status":"1","created_at":"2019-10-10 01:06:09","make_name":"Honda"},{"id":"1","make_id":"1","name":"Accord","status":"1","created_at":"2019-10-10 01:06:04","make_name":"Honda"}]}
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
         * id : 49
         * make_id : 1
         * name : Vezel
         * status : 1
         * created_at : 2019-10-10 01:08:04
         * make_name : Honda
         */

        private List<ModelBean> model;

        public List<ModelBean> getModel() {
            return model;
        }

        public void setModel(List<ModelBean> model) {
            this.model = model;
        }

        public static class ModelBean {
            private String id;
            private String make_id;
            private String name;
            private String status;
            private String created_at;
            private String make_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMake_id() {
                return make_id;
            }

            public void setMake_id(String make_id) {
                this.make_id = make_id;
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

            public String getMake_name() {
                return make_name;
            }

            public void setMake_name(String make_name) {
                this.make_name = make_name;
            }
        }
    }
}
