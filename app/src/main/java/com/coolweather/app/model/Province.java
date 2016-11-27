package com.coolweather.app.model;

/**
 * Created by wales on 16/11/21.
 */
public class Province {
    private int id;
    private String provinceEn, provinceZh;


    public boolean equals(Province p) {
        if (this.provinceZh == p.getProvinceZh())
            return true;

        else
            return false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }


}
