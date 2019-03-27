package com.kfit.crawl.bean.AlbumInfo;

public class Data {
    private int id;

    private String name;

    private int song_tag_id;

    private int special_tag_id;

    private int album_tag_id;

    private String imgurl;

    private String bannerurl;

    private String jump_url;

    private int is_new;

    private int has_child;

    private int is_publish;

    private String banner_hd;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSong_tag_id(int song_tag_id){
        this.song_tag_id = song_tag_id;
    }
    public int getSong_tag_id(){
        return this.song_tag_id;
    }
    public void setSpecial_tag_id(int special_tag_id){
        this.special_tag_id = special_tag_id;
    }
    public int getSpecial_tag_id(){
        return this.special_tag_id;
    }
    public void setAlbum_tag_id(int album_tag_id){
        this.album_tag_id = album_tag_id;
    }
    public int getAlbum_tag_id(){
        return this.album_tag_id;
    }
    public void setImgurl(String imgurl){
        this.imgurl = imgurl;
    }
    public String getImgurl(){
        return this.imgurl;
    }
    public void setBannerurl(String bannerurl){
        this.bannerurl = bannerurl;
    }
    public String getBannerurl(){
        return this.bannerurl;
    }
    public void setJump_url(String jump_url){
        this.jump_url = jump_url;
    }
    public String getJump_url(){
        return this.jump_url;
    }
    public void setIs_new(int is_new){
        this.is_new = is_new;
    }
    public int getIs_new(){
        return this.is_new;
    }
    public void setHas_child(int has_child){
        this.has_child = has_child;
    }
    public int getHas_child(){
        return this.has_child;
    }
    public void setIs_publish(int is_publish){
        this.is_publish = is_publish;
    }
    public int getIs_publish(){
        return this.is_publish;
    }
    public void setBanner_hd(String banner_hd){
        this.banner_hd = banner_hd;
    }
    public String getBanner_hd(){
        return this.banner_hd;
    }

}
