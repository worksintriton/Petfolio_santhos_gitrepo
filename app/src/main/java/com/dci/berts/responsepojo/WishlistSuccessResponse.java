package com.dci.berts.responsepojo;

import java.util.List;

public class WishlistSuccessResponse {


    /**
     * status : Success
     * code : 200
     * message : Wishlist Added successful
     * data : {"wishlist":[{"wishlist_id":"4","userid":"541","wishlist_product_id":"3","price":"10000","title":"Alloy Wheel ","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]},{"wishlist_id":"9","userid":"541","wishlist_product_id":"4","price":"10000","title":"Engine Piston","images":[{"id":"344","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c0265723ce.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02659c654.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c0265d21ee.jpg","image_order":"1","storage":"local"},{"id":"345","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c026f2f630.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c026f4b440.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c026f67bf5.jpg","image_order":"2","storage":"local"},{"id":"346","product_id":"4","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608c02771cb3c.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608c02773d836.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608c02775e7d0.jpg","image_order":"3","storage":"local"}]}]}
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
         * wishlist_id : 4
         * userid : 541
         * wishlist_product_id : 3
         * price : 10000
         * title : Alloy Wheel
         * images : [{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]
         */

        private List<WishlistBean> wishlist;

        public List<WishlistBean> getWishlist() {
            return wishlist;
        }

        public void setWishlist(List<WishlistBean> wishlist) {
            this.wishlist = wishlist;
        }

        public static class WishlistBean {
            private String wishlist_id;
            private String userid;
            private String wishlist_product_id;
            private String price;
            private String title;
            /**
             * id : 341
             * product_id : 3
             * image_default : http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg
             * image_big : http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg
             * image_small : http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg
             * image_order : 1
             * storage : local
             */

            private List<ImagesBean> images;

            public String getWishlist_id() {
                return wishlist_id;
            }

            public void setWishlist_id(String wishlist_id) {
                this.wishlist_id = wishlist_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getWishlist_product_id() {
                return wishlist_product_id;
            }

            public void setWishlist_product_id(String wishlist_product_id) {
                this.wishlist_product_id = wishlist_product_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean {
                private String id;
                private String product_id;
                private String image_default;
                private String image_big;
                private String image_small;
                private String image_order;
                private String storage;

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

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getImage_big() {
                    return image_big;
                }

                public void setImage_big(String image_big) {
                    this.image_big = image_big;
                }

                public String getImage_small() {
                    return image_small;
                }

                public void setImage_small(String image_small) {
                    this.image_small = image_small;
                }

                public String getImage_order() {
                    return image_order;
                }

                public void setImage_order(String image_order) {
                    this.image_order = image_order;
                }

                public String getStorage() {
                    return storage;
                }

                public void setStorage(String storage) {
                    this.storage = storage;
                }
            }
        }
    }
}
