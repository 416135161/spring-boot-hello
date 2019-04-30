package com.kfit.music.tools;

import com.kfit.crawl.tools.AcquireDataTool;
import com.kfit.music.bean.Song;

import java.util.ArrayList;
import java.util.List;

public class SongTool {

    public static boolean reassemblySong(List<Song> songList) {
        if (songList == null || songList.size() == 0) {
            return false;
        }
        List<Song> newSongList = new ArrayList<>();
        for (Song item : songList) {
            Song song = Transform.detailResponse2Song(AcquireDataTool.getSongDetail(item.getHash()));
            if(!TextUtil.isEmpty(song.getPlayUrl()) && !TextUtil.isEmpty(song.getSongName())){
                item.setImgUrl(song.getImgUrl());
                item.setSingerName(song.getSingerName());
                item.setSongName(song.getSongName());
                newSongList.add(item);
            }
        }
        songList.clear();
        songList.addAll(newSongList);
        return true;
    }
}
