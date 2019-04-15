package com.kfit.crawl.bean.europ;

import java.util.List;

public class EuropSongListResponse {
    private int status;
    private String error;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
         * timestamp : 1555165350
         * info : [{"filename":"Kelsea Ballerini - Graveyard","hash":"8BA6391597A5713B1DA5990F243D59DD"}]
         * total : 16
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
             * filename : Kelsea Ballerini - Graveyard
             * hash : 8BA6391597A5713B1DA5990F243D59DD
             */

            private String filename;
            private String hash;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }
        }
    }
}
