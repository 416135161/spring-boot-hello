package com.kfit.crawl.bean.rankList;

import java.util.List;

public class Info {
    private List<Vols> vols ;

    private int year;

    public void setVols(List<Vols> vols){
        this.vols = vols;
    }
    public List<Vols> getVols(){
        return this.vols;
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getYear(){
        return this.year;
    }
}
