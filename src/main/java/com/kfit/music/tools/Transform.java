package com.kfit.music.tools;

import com.kfit.crawl.bean.search.Info;
import com.kfit.crawl.bean.search.SearchResponseBean;
import com.kfit.crawl.bean.songDetail.Data;
import com.kfit.crawl.bean.songDetail.SongDetailBean;
import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;

import java.util.ArrayList;
import java.util.List;

public final class Transform {

    private Transform() {

    }

    public synchronized static List<Song> searchResponse2Song(SearchResponseBean searchResponseBean) {
        List<Song> songList = new ArrayList<>();
        if (searchResponseBean != null && searchResponseBean.getData() != null &&
                searchResponseBean.getData().getInfo() != null) {
            List<Info> infoList = searchResponseBean.getData().getInfo();
            if (infoList.size() > 0) {
                for (Info info : infoList) {
                    if (!TextUtil.isEmpty(info.getHash())) {
                        Song song = new Song();
                        song.setSongName(info.getSongname());
                        song.setHash(info.getHash());
                        song.setSingerName(info.getSingername());
                        song.setDuration(info.getDuration());
                        songList.add(song);
                    }
                }
            }

        }
        return songList;
    }

    public synchronized static Song detailResponse2Song(SongDetailBean songDetailBean) {

        Song song = new Song();
        if (songDetailBean != null && songDetailBean.getData() != null) {
            Data data = songDetailBean.getData();
            song.setSongName(data.getSong_name());
            song.setHash(data.getHash());
            song.setSingerName(data.getAuthor_name());
            song.setDuration(data.getTimelength() / 1000);
            song.setPlayUrl(data.getPlay_url());
            song.setLrc(data.getLyrics());
            song.setImgUrl(data.getImg());
        }
        return song;
    }


    public synchronized static List<Album> transformAlbumList(List<com.kfit.crawl.bean.AlbumList.Info> infoList) {
        if (infoList != null && infoList.size() > 0) {
            List<Album> albumList = new ArrayList<>();
            for (com.kfit.crawl.bean.AlbumList.Info item : infoList) {
                Album album = new Album();
                album.setId(item.getSpecialid());
                album.setImgUrl(item.getImgurl().replace("{size}", "240"));
                albumList.add(album);
            }
            return albumList;
        }
        return null;
    }


    public synchronized static List<Song> transformAlbumSongList(List<com.kfit.crawl.bean.AlbumSongList.Info> infoList) {
        if (infoList != null && infoList.size() > 0) {
            List<Song> songList = new ArrayList<>();
            for (com.kfit.crawl.bean.AlbumSongList.Info item : infoList) {
                Song song = new Song();
                song.setHash(item.getHash());
                if (item.getFilename() != null && item.getFilename().contains("-")) {
                    String[] names = item.getFilename().split("-");
                    song.setSingerName(names[0]);
                    song.setSongName(names[1]);
                }
                songList.add(song);
            }
            return songList;
        }
        return null;
    }


    public synchronized static List<Song> transformRandSongList(List<com.kfit.crawl.bean.ranksongs.Info> infoList) {
        if (infoList != null && infoList.size() > 0) {
            List<Song> songList = new ArrayList<>();
            for (com.kfit.crawl.bean.ranksongs.Info item : infoList) {
                Song song = new Song();
                song.setHash(item.getHash());
                if (item.getFilename() != null && item.getFilename().contains("-")) {
                    String[] names = item.getFilename().split("-");
                    song.setSingerName(names[0]);
                    song.setSongName(names[1]);
                }
                songList.add(song);
            }
            return songList;
        }
        return null;
    }

}
