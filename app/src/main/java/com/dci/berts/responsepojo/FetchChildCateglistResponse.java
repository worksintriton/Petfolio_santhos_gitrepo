package com.dci.berts.responsepojo;

import java.util.List;

public class FetchChildCateglistResponse {


    /**
     * status : Success
     * code : 200
     * message : Categories list successful
     * data : {"categories":[{"id":"5","slug":"car-audio","parent_id":"1","top_parent_id":"1","category_level":"2","parent_slug":"electronics","top_parent_slug":"electronics","description":"","keywords":"","category_order":"2","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_484-272_6078cc12cbf1d.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078cc12e1c9c.jpg","created_at":"2019-10-08 03:42:42","lang_id":"1","name":"Car audio"},{"id":"30","slug":"interior-car-accessories","parent_id":"1","top_parent_id":"1","category_level":"2","parent_slug":"electronics","top_parent_slug":"electronics","description":"Auto rubber bushing: engine mount, strut mount, center bearing, differential mount, control arm bushing, stabilizer bushing, other suspension bushing Suspension Parts: shock absorber, control arm, ball joint,stabilizer link, tie rod end, steering rack CV joint, drive shaft, cv joint boot Brake parts: brake pads, brake disc, brake master cylinder, wheel cylinder Fuel pump, water pump, radiator, gasket kit, engine beltAuto rubber bushing: engine mount, strut mount, center bearing, differential mou","keywords":"engine mount, strut mount, center bearing, differential mount, control arm bushing, stabilizer bushing, other suspension bushing Suspension Parts","category_order":"6","homepage_order":"8","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_451-254_6078c784e18bf.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078c7850325b.jpg","created_at":"2019-11-07 22:00:54","lang_id":"1","name":"Interior Car Accessories"},{"id":"19","slug":"mirror","parent_id":"1","top_parent_id":"1","category_level":"2","parent_slug":"electronics","top_parent_slug":"electronics","description":"","keywords":"","category_order":"465","homepage_order":"1434","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_172-97_6078cb8013510.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078cb801700c.jpg","created_at":"2019-10-15 05:22:01","lang_id":"1","name":"Mirror"}]}
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
         * id : 5
         * slug : car-audio
         * parent_id : 1
         * top_parent_id : 1
         * category_level : 2
         * parent_slug : electronics
         * top_parent_slug : electronics
         * description :
         * keywords :
         * category_order : 2
         * homepage_order : 1
         * visibility : 1
         * show_on_homepage : 0
         * storage : local
         * image_1 : http://dciinternal.in/dev01/uploads/category/category_484-272_6078cc12cbf1d.jpg
         * image_2 : http://dciinternal.in/dev01/uploads/category/category_440-541_6078cc12e1c9c.jpg
         * created_at : 2019-10-08 03:42:42
         * lang_id : 1
         * name : Car audio
         */

        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private String id;
            private String slug;
            private String parent_id;
            private String top_parent_id;
            private String category_level;
            private String parent_slug;
            private String top_parent_slug;
            private String description;
            private String keywords;
            private String category_order;
            private String homepage_order;
            private String visibility;
            private String show_on_homepage;
            private String storage;
            private String image_1;
            private String image_2;
            private String created_at;
            private String lang_id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getTop_parent_id() {
                return top_parent_id;
            }

            public void setTop_parent_id(String top_parent_id) {
                this.top_parent_id = top_parent_id;
            }

            public String getCategory_level() {
                return category_level;
            }

            public void setCategory_level(String category_level) {
                this.category_level = category_level;
            }

            public String getParent_slug() {
                return parent_slug;
            }

            public void setParent_slug(String parent_slug) {
                this.parent_slug = parent_slug;
            }

            public String getTop_parent_slug() {
                return top_parent_slug;
            }

            public void setTop_parent_slug(String top_parent_slug) {
                this.top_parent_slug = top_parent_slug;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getCategory_order() {
                return category_order;
            }

            public void setCategory_order(String category_order) {
                this.category_order = category_order;
            }

            public String getHomepage_order() {
                return homepage_order;
            }

            public void setHomepage_order(String homepage_order) {
                this.homepage_order = homepage_order;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getShow_on_homepage() {
                return show_on_homepage;
            }

            public void setShow_on_homepage(String show_on_homepage) {
                this.show_on_homepage = show_on_homepage;
            }

            public String getStorage() {
                return storage;
            }

            public void setStorage(String storage) {
                this.storage = storage;
            }

            public String getImage_1() {
                return image_1;
            }

            public void setImage_1(String image_1) {
                this.image_1 = image_1;
            }

            public String getImage_2() {
                return image_2;
            }

            public void setImage_2(String image_2) {
                this.image_2 = image_2;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getLang_id() {
                return lang_id;
            }

            public void setLang_id(String lang_id) {
                this.lang_id = lang_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
