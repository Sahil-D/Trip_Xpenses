package com.example.tripxpenses.CardModel;

public class TotalModel {
    String sno;
    String totalexp;
    String totalpname;

//    public TotalModel(int sno,String totalexp, String totalpname) {
//        this.sno=sno;
//        this.totalexp = totalexp;
//        this.totalpname = totalpname;
//    }


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTotalexp() {
        return totalexp;
    }

    public void setTotalexp(String totalexp) {
        this.totalexp = totalexp;
    }

    public String getTotalpname() {
        return totalpname;
    }

    public void setTotalpname(String totalpname) {
        this.totalpname = totalpname;
    }
}
