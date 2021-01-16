package com.example.tripxpenses.CardModel;

public class APersonModel {
    String pnum;
    String pname;

    public APersonModel(String pnum, String pname) {
        this.pnum = pnum;
        this.pname = pname;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
