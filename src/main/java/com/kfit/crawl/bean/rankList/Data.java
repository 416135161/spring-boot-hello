package com.kfit.crawl.bean.rankList;

import java.util.List;

public class Data {
    private int timestamp;

    private List<Info> info ;

    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
    public int getTimestamp(){
        return this.timestamp;
    }
    public void setInfo(List<Info> info){
        this.info = info;
    }
    public List<Info> getInfo(){
        return this.info;
    }
}
