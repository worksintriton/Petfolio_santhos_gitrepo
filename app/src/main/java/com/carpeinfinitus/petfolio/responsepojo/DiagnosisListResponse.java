package com.carpeinfinitus.petfolio.responsepojo;

import java.util.List;

public class DiagnosisListResponse {

    /**
     * Status : Success
     * Message : diagnosis Details
     * Data : [{"_id":"60af2d8eb46b7e1e2402cce5","diagnosis":"Diagnosis","date_and_time":"23-10-2023 11:00 AM","delete_status":false,"updatedAt":"2021-05-27T05:26:38.258Z","createdAt":"2021-05-27T05:26:38.258Z","__v":0},{"_id":"60af2dd3e735cb1e6d2d9034","diagnosis":"Diagnosis - 1","date_and_time":"23-10-2023 11:00 AM","delete_status":false,"updatedAt":"2021-05-27T05:27:47.991Z","createdAt":"2021-05-27T05:27:47.991Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60af2d8eb46b7e1e2402cce5
     * diagnosis : Diagnosis
     * date_and_time : 23-10-2023 11:00 AM
     * delete_status : false
     * updatedAt : 2021-05-27T05:26:38.258Z
     * createdAt : 2021-05-27T05:26:38.258Z
     * __v : 0
     */

    private List<DataBean> Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String _id;
        private String diagnosis;
        private String date_and_time;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private boolean isSelected ;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
