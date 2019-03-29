package com.kfit.crawl.bean.rankList;

public class RankResponseBean {
    private int status;

    private String error;

    private Data data;

    private int errcode;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return this.error;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setErrcode(int errcode){
        this.errcode = errcode;
    }
    public int getErrcode(){
        return this.errcode;
    }
}
