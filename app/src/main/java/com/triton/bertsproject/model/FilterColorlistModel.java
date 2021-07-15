package com.triton.bertsproject.model;

public class FilterColorlistModel {

    String flistname;

    boolean ischecked;

    int colorlist;

    public FilterColorlistModel(String flistname, boolean ischecked, int colorlist) {
        this.flistname = flistname;
        this.ischecked = ischecked;
        this.colorlist = colorlist;
    }

    public String getFlistname() {
        return flistname;
    }

    public void setFlistname(String flistname) {
        this.flistname = flistname;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public int getColorlist() {
        return colorlist;
    }

    public void setColorlist(int colorlist) {
        this.colorlist = colorlist;
    }
}
