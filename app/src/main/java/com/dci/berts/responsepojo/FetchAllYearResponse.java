package com.dci.berts.responsepojo;

import java.util.List;

public class FetchAllYearResponse {


    /**
     * status : Success
     * code : 200
     * message : Years list successful
     * data : {"year":[{"id":"10","product_id":"2","p_year":"2006"},{"id":"9","product_id":"2","p_year":"2007"},{"id":"8","product_id":"2","p_year":"2008"},{"id":"7","product_id":"2","p_year":"2009"},{"id":"4","product_id":"3","p_year":"2010"},{"id":"5","product_id":"2","p_year":"2011"},{"id":"3","product_id":"3","p_year":"2012"},{"id":"18","product_id":"4","p_year":"2013"},{"id":"2","product_id":"3","p_year":"2014"},{"id":"1","product_id":"3","p_year":"2015"},{"id":"15","product_id":"4","p_year":"2016"},{"id":"14","product_id":"4","p_year":"2017"},{"id":"13","product_id":"4","p_year":"2018"},{"id":"12","product_id":"4","p_year":"2019"},{"id":"11","product_id":"4","p_year":"2020"}]}
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
         * id : 10
         * product_id : 2
         * p_year : 2006
         */

        private List<YearBean> year;

        public List<YearBean> getYear() {
            return year;
        }

        public void setYear(List<YearBean> year) {
            this.year = year;
        }

        public static class YearBean {
            private String id;
            private String product_id;
            private String p_year;

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

            public String getP_year() {
                return p_year;
            }

            public void setP_year(String p_year) {
                this.p_year = p_year;
            }
        }
    }
}
