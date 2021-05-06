package com.petfolio.infinitus.responsepojo;

import java.util.List;

public class DoctorDetailsResponse {


    /**
     * Status : Success
     * Message : Docotor Details
     * Data : {"_id":"60791f8e0821fa2656160a2c","user_id":"60791e99793d48701d98f51b","dr_title":"Dr","dr_name":"Dinesh","clinic_name":"Apollo clinic","clinic_loc":"4, 1st Link Rd, Puzhuthivakkam, Sadasiva Nagar, Madipakkam, Chennai, Tamil Nadu 600091, India","clinic_lat":10.7938439,"clinic_long":78.6857356,"education_details":[{"year":"2019","education":"MD"}],"experience_details":[{"yearsofexperience":3,"to":"2020","company":"SRM HOSPITAL","from":"2017"}],"specialization":[{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"}],"pet_handled":[{"pet_handled":"Dog"},{"pet_handled":"Cat"},{"pet_handled":"Parrot"},{"pet_handled":"Pigs"}],"clinic_pic":[{"clinic_pic":"http://54.212.108.156:3000/api/uploads/1618550607731.jpg"}],"certificate_pic":[{"certificate_pic":"http://54.212.108.156:3000/api/uploads/1618550631073.PDF"}],"govt_id_pic":[{"govt_id_pic":"http://54.212.108.156:3000/api/uploads/1618550641426.PDF"}],"photo_id_pic":[{"photo_id_pic":"http://54.212.108.156:3000/api/uploads/1618550651333.PDF"}],"profile_status":true,"profile_verification_status":"Last update not verified","slot_type":"","date_and_time":"16-04-2021 04:19 PM","descri":"A class of medical instruction in which patients are examined and discussed. 2 : a group meeting devoted to the analysis and solution of concrete problems or to the acquiring of specific skills or knowledge writing clinics golf clinic","star_count":4,"review_count":223,"amount":200,"mobile_type":"IOS","communication_type":"Online Or Visit","doctor_exp":3,"signature":"http://54.212.108.156:3000/api/uploads/1618552491813.png"}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 60791f8e0821fa2656160a2c
     * user_id : 60791e99793d48701d98f51b
     * dr_title : Dr
     * dr_name : Dinesh
     * clinic_name : Apollo clinic
     * clinic_loc : 4, 1st Link Rd, Puzhuthivakkam, Sadasiva Nagar, Madipakkam, Chennai, Tamil Nadu 600091, India
     * clinic_lat : 10.7938439
     * clinic_long : 78.6857356
     * education_details : [{"year":"2019","education":"MD"}]
     * experience_details : [{"yearsofexperience":3,"to":"2020","company":"SRM HOSPITAL","from":"2017"}]
     * specialization : [{"specialization":"Surgeon"},{"specialization":"Internal Medicine Physician"},{"specialization":"Psychiatrist"}]
     * pet_handled : [{"pet_handled":"Dog"},{"pet_handled":"Cat"},{"pet_handled":"Parrot"},{"pet_handled":"Pigs"}]
     * clinic_pic : [{"clinic_pic":"http://54.212.108.156:3000/api/uploads/1618550607731.jpg"}]
     * certificate_pic : [{"certificate_pic":"http://54.212.108.156:3000/api/uploads/1618550631073.PDF"}]
     * govt_id_pic : [{"govt_id_pic":"http://54.212.108.156:3000/api/uploads/1618550641426.PDF"}]
     * photo_id_pic : [{"photo_id_pic":"http://54.212.108.156:3000/api/uploads/1618550651333.PDF"}]
     * profile_status : true
     * profile_verification_status : Last update not verified
     * slot_type :
     * date_and_time : 16-04-2021 04:19 PM
     * descri : A class of medical instruction in which patients are examined and discussed. 2 : a group meeting devoted to the analysis and solution of concrete problems or to the acquiring of specific skills or knowledge writing clinics golf clinic
     * star_count : 4
     * review_count : 223
     * amount : 200
     * mobile_type : IOS
     * communication_type : Online Or Visit
     * doctor_exp : 3
     * signature : http://54.212.108.156:3000/api/uploads/1618552491813.png
     */

    private DataBean Data;
    private int Code;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;
        private String user_id;
        private String dr_title;
        private String dr_name;
        private String clinic_name;
        private String clinic_loc;
        private double clinic_lat;
        private double clinic_long;
        private boolean profile_status;
        private String profile_verification_status;
        private String slot_type;
        private String date_and_time;
        private String descri;
        private int star_count;
        private int review_count;
        private int amount;
        private String mobile_type;
        private String communication_type;
        private int doctor_exp;
        private String signature;
        /**
         * year : 2019
         * education : MD
         */

        private List<EducationDetailsBean> education_details;
        /**
         * yearsofexperience : 3
         * to : 2020
         * company : SRM HOSPITAL
         * from : 2017
         */

        private List<ExperienceDetailsBean> experience_details;
        /**
         * specialization : Surgeon
         */

        private List<SpecializationBean> specialization;
        /**
         * pet_handled : Dog
         */

        private List<PetHandledBean> pet_handled;
        /**
         * clinic_pic : http://54.212.108.156:3000/api/uploads/1618550607731.jpg
         */

        private List<ClinicPicBean> clinic_pic;
        /**
         * certificate_pic : http://54.212.108.156:3000/api/uploads/1618550631073.PDF
         */

        private List<CertificatePicBean> certificate_pic;
        /**
         * govt_id_pic : http://54.212.108.156:3000/api/uploads/1618550641426.PDF
         */

        private List<GovtIdPicBean> govt_id_pic;
        /**
         * photo_id_pic : http://54.212.108.156:3000/api/uploads/1618550651333.PDF
         */

        private List<PhotoIdPicBean> photo_id_pic;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDr_title() {
            return dr_title;
        }

        public void setDr_title(String dr_title) {
            this.dr_title = dr_title;
        }

        public String getDr_name() {
            return dr_name;
        }

        public void setDr_name(String dr_name) {
            this.dr_name = dr_name;
        }

        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public String getClinic_loc() {
            return clinic_loc;
        }

        public void setClinic_loc(String clinic_loc) {
            this.clinic_loc = clinic_loc;
        }

        public double getClinic_lat() {
            return clinic_lat;
        }

        public void setClinic_lat(double clinic_lat) {
            this.clinic_lat = clinic_lat;
        }

        public double getClinic_long() {
            return clinic_long;
        }

        public void setClinic_long(double clinic_long) {
            this.clinic_long = clinic_long;
        }

        public boolean isProfile_status() {
            return profile_status;
        }

        public void setProfile_status(boolean profile_status) {
            this.profile_status = profile_status;
        }

        public String getProfile_verification_status() {
            return profile_verification_status;
        }

        public void setProfile_verification_status(String profile_verification_status) {
            this.profile_verification_status = profile_verification_status;
        }

        public String getSlot_type() {
            return slot_type;
        }

        public void setSlot_type(String slot_type) {
            this.slot_type = slot_type;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getDescri() {
            return descri;
        }

        public void setDescri(String descri) {
            this.descri = descri;
        }

        public int getStar_count() {
            return star_count;
        }

        public void setStar_count(int star_count) {
            this.star_count = star_count;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public String getCommunication_type() {
            return communication_type;
        }

        public void setCommunication_type(String communication_type) {
            this.communication_type = communication_type;
        }

        public int getDoctor_exp() {
            return doctor_exp;
        }

        public void setDoctor_exp(int doctor_exp) {
            this.doctor_exp = doctor_exp;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public List<EducationDetailsBean> getEducation_details() {
            return education_details;
        }

        public void setEducation_details(List<EducationDetailsBean> education_details) {
            this.education_details = education_details;
        }

        public List<ExperienceDetailsBean> getExperience_details() {
            return experience_details;
        }

        public void setExperience_details(List<ExperienceDetailsBean> experience_details) {
            this.experience_details = experience_details;
        }

        public List<SpecializationBean> getSpecialization() {
            return specialization;
        }

        public void setSpecialization(List<SpecializationBean> specialization) {
            this.specialization = specialization;
        }

        public List<PetHandledBean> getPet_handled() {
            return pet_handled;
        }

        public void setPet_handled(List<PetHandledBean> pet_handled) {
            this.pet_handled = pet_handled;
        }

        public List<ClinicPicBean> getClinic_pic() {
            return clinic_pic;
        }

        public void setClinic_pic(List<ClinicPicBean> clinic_pic) {
            this.clinic_pic = clinic_pic;
        }

        public List<CertificatePicBean> getCertificate_pic() {
            return certificate_pic;
        }

        public void setCertificate_pic(List<CertificatePicBean> certificate_pic) {
            this.certificate_pic = certificate_pic;
        }

        public List<GovtIdPicBean> getGovt_id_pic() {
            return govt_id_pic;
        }

        public void setGovt_id_pic(List<GovtIdPicBean> govt_id_pic) {
            this.govt_id_pic = govt_id_pic;
        }

        public List<PhotoIdPicBean> getPhoto_id_pic() {
            return photo_id_pic;
        }

        public void setPhoto_id_pic(List<PhotoIdPicBean> photo_id_pic) {
            this.photo_id_pic = photo_id_pic;
        }

        public static class EducationDetailsBean {
            private String year;
            private String education;

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }
        }

        public static class ExperienceDetailsBean {
            private int yearsofexperience;
            private String to;
            private String company;
            private String from;

            public int getYearsofexperience() {
                return yearsofexperience;
            }

            public void setYearsofexperience(int yearsofexperience) {
                this.yearsofexperience = yearsofexperience;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }
        }

        public static class SpecializationBean {
            private String specialization;

            public String getSpecialization() {
                return specialization;
            }

            public void setSpecialization(String specialization) {
                this.specialization = specialization;
            }
        }

        public static class PetHandledBean {
            private String pet_handled;

            public String getPet_handled() {
                return pet_handled;
            }

            public void setPet_handled(String pet_handled) {
                this.pet_handled = pet_handled;
            }
        }

        public static class ClinicPicBean {
            private String clinic_pic;

            public String getClinic_pic() {
                return clinic_pic;
            }

            public void setClinic_pic(String clinic_pic) {
                this.clinic_pic = clinic_pic;
            }
        }

        public static class CertificatePicBean {
            private String certificate_pic;

            public String getCertificate_pic() {
                return certificate_pic;
            }

            public void setCertificate_pic(String certificate_pic) {
                this.certificate_pic = certificate_pic;
            }
        }

        public static class GovtIdPicBean {
            private String govt_id_pic;

            public String getGovt_id_pic() {
                return govt_id_pic;
            }

            public void setGovt_id_pic(String govt_id_pic) {
                this.govt_id_pic = govt_id_pic;
            }
        }

        public static class PhotoIdPicBean {
            private String photo_id_pic;

            public String getPhoto_id_pic() {
                return photo_id_pic;
            }

            public void setPhoto_id_pic(String photo_id_pic) {
                this.photo_id_pic = photo_id_pic;
            }
        }
    }
}
