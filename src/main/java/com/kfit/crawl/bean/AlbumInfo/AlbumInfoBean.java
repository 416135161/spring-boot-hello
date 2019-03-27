package com.kfit.crawl.bean.AlbumInfo;

public class AlbumInfoBean {
    private int status;

    private int errcode;

    private String error;

    private Data data;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setErrcode(int errcode){
        this.errcode = errcode;
    }
    public int getErrcode(){
        return this.errcode;
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
}
