package com.dci.berts.model;

public class FilterlistModel {

    String flistname;

    boolean ischecked;

    public FilterlistModel(String flistname, boolean ischecked) {
        this.flistname = flistname;
        this.ischecked = ischecked;
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
}
