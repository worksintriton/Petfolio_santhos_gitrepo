package com.triton.bertsproject.responsepojo;

import java.util.List;

public class FetchAllParentCategoriesResponse {


    /**
     * status : Success
     * code : 200
     * message : Categories list successful
     * data : {"categories":[{"id":"1","slug":"electronics","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"Electronics","keywords":"Electronics","category_order":"1","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_581-327_6078ce48c912c.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078ce48e3c43.jpg","created_at":"2019-09-16 05:47:08","lang_id":"1","name":"Electronics"},{"id":"27","slug":"Car Paints","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"description for paints","keywords":"keyword for paints","category_order":"1","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_968-544_6078cd0673fb2.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078cd06d25a7.jpg","created_at":"2019-11-07 06:10:20","lang_id":"1","name":"Car & Bike paints"},{"id":"3","slug":"automotive","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"","keywords":"","category_order":"5","homepage_order":"5","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_496-279_6078cc90e4933.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078cc9102616.jpg","created_at":"2019-10-08 03:40:11","lang_id":"1","name":"Automotive"},{"id":"32","slug":"car-accessories","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"Keep your car tidy or add your own personal touch with our wide range of interior car accessories. A car bin is probably one of the handiest accessories you can buy for your car. These handy contraptions simply strap to the rear of the driver or passenger seat and provide the perfect place to stow your empty crisp packets and drink cans. If you have little ones, then Keep your car tidy or add your own personal touch with our wide range of interior car accessories. A car bin is probably one of th","keywords":"Keep your car tidy or add your own personal touch with our wide range of interior car accessories. A car bin is probably one of the handiest accessories you can buy for your car. These handy contraptions simply strap to the rear of the driver or passenger seat and provide the perfect place to stow your empty crisp packets and drink cans. If you have little ones, then Keep your car tidy or add your own personal touch with our wide range of interior car accessories. A car bin is probably one of th","category_order":"5","homepage_order":"5","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_199-112_6078c5f8eaeb7.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078c5f8efb44.jpg","created_at":"2019-11-07 22:08:46","lang_id":"1","name":"Car Accessories"},{"id":"43","slug":"new-motor","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"New type of motor is arrivel","keywords":"motor new bike","category_order":"10","homepage_order":"5","visibility":"1","show_on_homepage":"1","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_165-93_6078c2dde9685.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078c2ddecf89.jpg","created_at":"2019-11-08 05:43:42","lang_id":"1","name":"New Motor"},{"id":"17","slug":"brakes","parent_id":"0","top_parent_id":"0","category_level":"1","parent_slug":"","top_parent_slug":"","description":"","keywords":"","category_order":"12","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_150-85_6078cbc81e31b.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_6078cbc82136f.jpg","created_at":"2019-10-14 03:43:32","lang_id":"1","name":"Brakes"}]}
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
         * slug : electronics
         * parent_id : 0
         * top_parent_id : 0
         * category_level : 1
         * parent_slug :
         * top_parent_slug :
         * description : Electronics
         * keywords : Electronics
         * category_order : 1
         * homepage_order : 1
         * visibility : 1
         * show_on_homepage : 0
         * storage : local
         * image_1 : http://dciinternal.in/dev01/uploads/category/category_581-327_6078ce48c912c.jpg
         * image_2 : http://dciinternal.in/dev01/uploads/category/category_440-541_6078ce48e3c43.jpg
         * created_at : 2019-09-16 05:47:08
         * lang_id : 1
         * name : Electronics
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
