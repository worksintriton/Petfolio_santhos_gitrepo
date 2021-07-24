package com.dci.berts.model;

public class ShoplistModel {

    String shoplistname;

    int image;

    public ShoplistModel(String shoplistname, int image) {
        this.shoplistname = shoplistname;
        this.image = image;
    }

    public String getShoplistname() {
        return shoplistname;
    }

    public void setShoplistname(String shoplistname) {
        this.shoplistname = shoplistname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
