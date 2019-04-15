package com.kfit.crawl.bean.europ;

import java.util.List;

public class EuropAlbumListResopnse {

    /**
     * status : 1
     * errcode : 0
     * error :
     * data : {"timestamp":1555164473,"total":374,"info":[{"albumid":17659046,"albumname":"A Place We Knew","singerid":676397,"singername":"Dean Lewis",
     * "publishtime":"2019-03-22 00:00:00","imgurl":"http://imge.kugou.com/stdmusic/{size}/20190322/20190322101502158683.jpg","privilege":0}]}
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
         * timestamp : 1555164473
         * total : 374
         * info : [{"albumid":17659046,"albumname":"A Place We Knew","singerid":676397,"singername":"Dean Lewis","publishtime":"2019-03-22 00:00:00",
         * "imgurl":"http://imge.kugou.com/stdmusic/{size}/20190322/20190322101502158683.jpg","privilege":0}]
         */

        private int timestamp;
        private int total;
        private List<InfoBean> info;

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * albumid : 17659046
             * albumname : A Place We Knew
             * singerid : 676397
             * singername : Dean Lewis
             * publishtime : 2019-03-22 00:00:00
             * imgurl : http://imge.kugou.com/stdmusic/{size}/20190322/20190322101502158683.jpg
             * privilege : 0
             */

            private int albumid;
            private String albumname;
            private int singerid;
            private String singername;
            private String publishtime;
            private String imgurl;
            private int privilege;

            public int getAlbumid() {
                return albumid;
            }

            public void setAlbumid(int albumid) {
                this.albumid = albumid;
            }

            public String getAlbumname() {
                return albumname;
            }

            public void setAlbumname(String albumname) {
                this.albumname = albumname;
            }

            public int getSingerid() {
                return singerid;
            }

            public void setSingerid(int singerid) {
                this.singerid = singerid;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getPublishtime() {
                return publishtime;
            }

            public void setPublishtime(String publishtime) {
                this.publishtime = publishtime;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public int getPrivilege() {
                return privilege;
            }

            public void setPrivilege(int privilege) {
                this.privilege = privilege;
            }
        }
    }
}
