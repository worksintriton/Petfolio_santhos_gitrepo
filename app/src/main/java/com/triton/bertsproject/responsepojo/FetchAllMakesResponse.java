package com.triton.bertsproject.responsepojo;

import java.util.List;

public class FetchAllMakesResponse {


    /**
     * status : true
     * error_code : 0
     * error_message :
     * data : {"makes":[{"id":"45","name":"Porsche","logo":"http://dciinternal.in/dev01/assets/img/make/Porsche.jpg","status":"1","created_at":"2020-09-11 00:22:03"},{"id":"44","name":"Mercedes-benz","logo":"http://dciinternal.in/dev01/assets/img/make/Mercedes-benz.png","status":"1","created_at":"2020-09-11 00:19:28"},{"id":"43","name":"Chrysler","logo":"http://dciinternal.in/dev01/assets/img/make/Chrysler.jpg","status":"1","created_at":"2020-09-11 00:18:58"},{"id":"42","name":"Peugeot","logo":"http://dciinternal.in/dev01/assets/img/make/Peugeot.png","status":"1","created_at":"2020-09-11 00:18:49"},{"id":"41","name":"Santana motor","logo":"http://dciinternal.in/dev01/assets/img/make/Santanamotor.png","status":"1","created_at":"2020-09-11 00:11:58"},{"id":"40","name":"Chevrolet (gm)","logo":"http://dciinternal.in/dev01/assets/img/make/Chevrolet.png","status":"1","created_at":"2020-09-11 00:11:56"},{"id":"39","name":"Datsun","logo":"http://dciinternal.in/dev01/assets/img/make/Datsun.jpg","status":"1","created_at":"2020-09-11 00:11:53"},{"id":"38","name":"Holden","logo":"http://dciinternal.in/dev01/assets/img/make/Holden.jpg","status":"1","created_at":"2020-09-11 00:10:15"},{"id":"37","name":"Acura","logo":"http://dciinternal.in/dev01/assets/img/make/Acura.jpg","status":"1","created_at":"2020-09-11 00:10:14"},{"id":"35","name":"Ud","logo":"http://dciinternal.in/dev01/assets/img/make/Ud.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"34","name":"Scion","logo":"http://dciinternal.in/dev01/assets/img/make/Scion.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"33","name":"Jaguar","logo":"http://dciinternal.in/dev01/assets/img/make/Jaguar.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"32","name":"Ford","logo":"http://dciinternal.in/dev01/assets/img/make/Ford.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"31","name":"Dodge","logo":"http://dciinternal.in/dev01/assets/img/make/Dodge.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"30","name":"Citroen","logo":"http://dciinternal.in/dev01/assets/img/make/Citroen.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"29","name":"Ssangyong","logo":"http://dciinternal.in/dev01/assets/img/make/Ssangyong.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"28","name":"Volkswagen","logo":"http://dciinternal.in/dev01/assets/img/make/Volkswagen.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"27","name":"Chevrolet","logo":"http://dciinternal.in/dev01/assets/img/make/Chevrolet.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"26","name":"Subaru","logo":"http://dciinternal.in/dev01/assets/img/make/subaru.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"25","name":"Renault","logo":"http://dciinternal.in/dev01/assets/img/make/Renault.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"24","name":"Lada","logo":"http://dciinternal.in/dev01/assets/img/make/Lada.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"23","name":"Kia","logo":"http://dciinternal.in/dev01/assets/img/make/Kia.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"22","name":"Jeep","logo":"http://dciinternal.in/dev01/assets/img/make/Jeep.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"21","name":"Hyundai","logo":"http://dciinternal.in/dev01/assets/img/make/Hyundai.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"20","name":"Land Rover","logo":"http://dciinternal.in/dev01/assets/img/make/LandRover.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"19","name":"Alfa Romeo","logo":"http://dciinternal.in/dev01/assets/img/make/AlfaRomeo.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"18","name":"Bmw","logo":"http://dciinternal.in/dev01/assets/img/make/BMW.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"17","name":"Lancia","logo":"http://dciinternal.in/dev01/assets/img/make/Lancia.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"16","name":"Daihatsu","logo":"http://dciinternal.in/dev01/assets/img/make/Daihatsu.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"15","name":"Daewoo","logo":"http://dciinternal.in/dev01/assets/img/make/Daewoo.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"14","name":"Vauxhall","logo":"http://dciinternal.in/dev01/assets/img/make/vauxhall.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"13","name":"Fiat","logo":"http://dciinternal.in/dev01/assets/img/make/Fiat.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"12","name":"Opel","logo":"http://dciinternal.in/dev01/assets/img/make/opel.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"11","name":"Infiniti","logo":"http://dciinternal.in/dev01/assets/img/make/Infiniti.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"10","name":"Audi","logo":"http://dciinternal.in/dev01/assets/img/make/audi.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"9","name":"Lexus","logo":"http://dciinternal.in/dev01/assets/img/make/lexus.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"8","name":"Toyota","logo":"http://dciinternal.in/dev01/assets/img/make/Toyota.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"7","name":"Suzuki","logo":"http://dciinternal.in/dev01/assets/img/make/suzuki.png","status":"1","created_at":"2019-10-11 09:21:51"},{"id":"6","name":"Subaru","logo":"http://dciinternal.in/dev01/assets/img/make/subaru.png","status":"1","created_at":"2019-10-11 09:21:45"},{"id":"5","name":"Nissan","logo":"http://dciinternal.in/dev01/assets/img/make/Nissan.png","status":"1","created_at":"2019-10-11 09:21:40"},{"id":"4","name":"Mitsubishi","logo":"http://dciinternal.in/dev01/assets/img/make/Mitsubishi.png","status":"1","created_at":"2019-10-11 09:21:27"},{"id":"3","name":"Mazda","logo":"http://dciinternal.in/dev01/assets/img/make/Mazda@2x.png","status":"1","created_at":"2019-10-11 09:21:18"},{"id":"2","name":"Isuzu","logo":"http://dciinternal.in/dev01/assets/img/make/Isuzu_logo@2x.png","status":"1","created_at":"2019-10-11 09:21:11"},{"id":"1","name":"Honda","logo":"http://dciinternal.in/dev01/assets/img/make/Honda@2x.png","status":"1","created_at":"2019-10-11 09:21:05"}],"display_message":""}
     */

    private boolean status;
    private int error_code;
    private String error_message;
    /**
     * makes : [{"id":"45","name":"Porsche","logo":"http://dciinternal.in/dev01/assets/img/make/Porsche.jpg","status":"1","created_at":"2020-09-11 00:22:03"},{"id":"44","name":"Mercedes-benz","logo":"http://dciinternal.in/dev01/assets/img/make/Mercedes-benz.png","status":"1","created_at":"2020-09-11 00:19:28"},{"id":"43","name":"Chrysler","logo":"http://dciinternal.in/dev01/assets/img/make/Chrysler.jpg","status":"1","created_at":"2020-09-11 00:18:58"},{"id":"42","name":"Peugeot","logo":"http://dciinternal.in/dev01/assets/img/make/Peugeot.png","status":"1","created_at":"2020-09-11 00:18:49"},{"id":"41","name":"Santana motor","logo":"http://dciinternal.in/dev01/assets/img/make/Santanamotor.png","status":"1","created_at":"2020-09-11 00:11:58"},{"id":"40","name":"Chevrolet (gm)","logo":"http://dciinternal.in/dev01/assets/img/make/Chevrolet.png","status":"1","created_at":"2020-09-11 00:11:56"},{"id":"39","name":"Datsun","logo":"http://dciinternal.in/dev01/assets/img/make/Datsun.jpg","status":"1","created_at":"2020-09-11 00:11:53"},{"id":"38","name":"Holden","logo":"http://dciinternal.in/dev01/assets/img/make/Holden.jpg","status":"1","created_at":"2020-09-11 00:10:15"},{"id":"37","name":"Acura","logo":"http://dciinternal.in/dev01/assets/img/make/Acura.jpg","status":"1","created_at":"2020-09-11 00:10:14"},{"id":"35","name":"Ud","logo":"http://dciinternal.in/dev01/assets/img/make/Ud.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"34","name":"Scion","logo":"http://dciinternal.in/dev01/assets/img/make/Scion.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"33","name":"Jaguar","logo":"http://dciinternal.in/dev01/assets/img/make/Jaguar.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"32","name":"Ford","logo":"http://dciinternal.in/dev01/assets/img/make/Ford.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"31","name":"Dodge","logo":"http://dciinternal.in/dev01/assets/img/make/Dodge.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"30","name":"Citroen","logo":"http://dciinternal.in/dev01/assets/img/make/Citroen.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"29","name":"Ssangyong","logo":"http://dciinternal.in/dev01/assets/img/make/Ssangyong.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"28","name":"Volkswagen","logo":"http://dciinternal.in/dev01/assets/img/make/Volkswagen.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"27","name":"Chevrolet","logo":"http://dciinternal.in/dev01/assets/img/make/Chevrolet.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"26","name":"Subaru","logo":"http://dciinternal.in/dev01/assets/img/make/subaru.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"25","name":"Renault","logo":"http://dciinternal.in/dev01/assets/img/make/Renault.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"24","name":"Lada","logo":"http://dciinternal.in/dev01/assets/img/make/Lada.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"23","name":"Kia","logo":"http://dciinternal.in/dev01/assets/img/make/Kia.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"22","name":"Jeep","logo":"http://dciinternal.in/dev01/assets/img/make/Jeep.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"21","name":"Hyundai","logo":"http://dciinternal.in/dev01/assets/img/make/Hyundai.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"20","name":"Land Rover","logo":"http://dciinternal.in/dev01/assets/img/make/LandRover.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"19","name":"Alfa Romeo","logo":"http://dciinternal.in/dev01/assets/img/make/AlfaRomeo.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"18","name":"Bmw","logo":"http://dciinternal.in/dev01/assets/img/make/BMW.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"17","name":"Lancia","logo":"http://dciinternal.in/dev01/assets/img/make/Lancia.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"16","name":"Daihatsu","logo":"http://dciinternal.in/dev01/assets/img/make/Daihatsu.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"15","name":"Daewoo","logo":"http://dciinternal.in/dev01/assets/img/make/Daewoo.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"14","name":"Vauxhall","logo":"http://dciinternal.in/dev01/assets/img/make/vauxhall.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"13","name":"Fiat","logo":"http://dciinternal.in/dev01/assets/img/make/Fiat.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"12","name":"Opel","logo":"http://dciinternal.in/dev01/assets/img/make/opel.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"11","name":"Infiniti","logo":"http://dciinternal.in/dev01/assets/img/make/Infiniti.jpg","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"10","name":"Audi","logo":"http://dciinternal.in/dev01/assets/img/make/audi.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"9","name":"Lexus","logo":"http://dciinternal.in/dev01/assets/img/make/lexus.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"8","name":"Toyota","logo":"http://dciinternal.in/dev01/assets/img/make/Toyota.png","status":"1","created_at":"2019-10-11 09:22:01"},{"id":"7","name":"Suzuki","logo":"http://dciinternal.in/dev01/assets/img/make/suzuki.png","status":"1","created_at":"2019-10-11 09:21:51"},{"id":"6","name":"Subaru","logo":"http://dciinternal.in/dev01/assets/img/make/subaru.png","status":"1","created_at":"2019-10-11 09:21:45"},{"id":"5","name":"Nissan","logo":"http://dciinternal.in/dev01/assets/img/make/Nissan.png","status":"1","created_at":"2019-10-11 09:21:40"},{"id":"4","name":"Mitsubishi","logo":"http://dciinternal.in/dev01/assets/img/make/Mitsubishi.png","status":"1","created_at":"2019-10-11 09:21:27"},{"id":"3","name":"Mazda","logo":"http://dciinternal.in/dev01/assets/img/make/Mazda@2x.png","status":"1","created_at":"2019-10-11 09:21:18"},{"id":"2","name":"Isuzu","logo":"http://dciinternal.in/dev01/assets/img/make/Isuzu_logo@2x.png","status":"1","created_at":"2019-10-11 09:21:11"},{"id":"1","name":"Honda","logo":"http://dciinternal.in/dev01/assets/img/make/Honda@2x.png","status":"1","created_at":"2019-10-11 09:21:05"}]
     * display_message :
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
         * id : 45
         * name : Porsche
         * logo : http://dciinternal.in/dev01/assets/img/make/Porsche.jpg
         * status : 1
         * created_at : 2020-09-11 00:22:03
         */

        private List<MakesBean> makes;

        public String getDisplay_message() {
            return display_message;
        }

        public void setDisplay_message(String display_message) {
            this.display_message = display_message;
        }

        public List<MakesBean> getMakes() {
            return makes;
        }

        public void setMakes(List<MakesBean> makes) {
            this.makes = makes;
        }

        public static class MakesBean {
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
