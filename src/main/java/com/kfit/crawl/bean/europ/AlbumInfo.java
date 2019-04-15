package com.kfit.crawl.bean.europ;

public class AlbumInfo {

    /**
     * status : 1
     * errcode : 0
     * error :
     * data : {"id":1051,"name":"欧美流行","song_tag_id":0,"special_tag_id":0,"album_tag_id":834,"imgurl":"http://imge.kugou
     * .com/mcommon/{size}/20170119/20170119210625771331.jpg","bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/T1QJhVB4Kj1RCvBVdK.jpg",
     * "jump_url":"","is_new":0,"has_child":0,"is_publish":1,"banner_hd":"http://imge.kugou.com/mcommon/{size}/20170119/20170119210626783155.jpg"}
     */

    private int status;
    private int errcode;
    private String error;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1051
         * name : 欧美流行
         * song_tag_id : 0
         * special_tag_id : 0
         * album_tag_id : 834
         * imgurl : http://imge.kugou.com/mcommon/{size}/20170119/20170119210625771331.jpg
         * bannerurl : http://imge.kugou.com/v2/mobile_class_banner/{size}/T1QJhVB4Kj1RCvBVdK.jpg
         * jump_url :
         * is_new : 0
         * has_child : 0
         * is_publish : 1
         * banner_hd : http://imge.kugou.com/mcommon/{size}/20170119/20170119210626783155.jpg
         */

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSong_tag_id() {
            return song_tag_id;
        }

        public void setSong_tag_id(int song_tag_id) {
            this.song_tag_id = song_tag_id;
        }

        public int getSpecial_tag_id() {
            return special_tag_id;
        }

        public void setSpecial_tag_id(int special_tag_id) {
            this.special_tag_id = special_tag_id;
        }

        public int getAlbum_tag_id() {
            return album_tag_id;
        }

        public void setAlbum_tag_id(int album_tag_id) {
            this.album_tag_id = album_tag_id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getBannerurl() {
            return bannerurl;
        }

        public void setBannerurl(String bannerurl) {
            this.bannerurl = bannerurl;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public int getHas_child() {
            return has_child;
        }

        public void setHas_child(int has_child) {
            this.has_child = has_child;
        }

        public int getIs_publish() {
            return is_publish;
        }

        public void setIs_publish(int is_publish) {
            this.is_publish = is_publish;
        }

        public String getBanner_hd() {
            return banner_hd;
        }

        public void setBanner_hd(String banner_hd) {
            this.banner_hd = banner_hd;
        }
    }
}
