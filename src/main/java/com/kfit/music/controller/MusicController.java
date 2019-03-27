package com.kfit.music.controller;

import com.kfit.crawl.tools.AcquireDataTool;
import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;
import com.kfit.music.tools.Contants;
import com.kfit.music.tools.Transform;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/music")
public class MusicController {

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public List<Song> getNewSongs() {
        return null;
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Song> searchSongs(@RequestParam("keyword") String keyword, @RequestParam(value = "pageSize", required = false, defaultValue = "30") Integer pageSize) {
        return Transform.searchResponse2Song(AcquireDataTool.searchSongs(keyword, pageSize));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Song getSongDetail(@RequestParam("hash") String hash) {
        return Transform.detailResponse2Song(AcquireDataTool.getSongDetail(hash));
    }


    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public List<Album> getAlbums(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") Integer pageSize) {
        if(Contants.albumListMap != null && Contants.albumListMap.get(from) != null){
            return Contants.albumListMap.get(from);
        }
        try {
            int special_tag_id = AcquireDataTool.getAlbumInfo(from);
            if (special_tag_id == -1) {
                return new ArrayList<>();
            }
            List<Album> albumList = Transform.transformAlbumList(AcquireDataTool.getAlbumList(special_tag_id, pageSize));
            if (albumList == null || albumList.size() == 0) {
                return new ArrayList<>();
            }
            for (Album album : albumList) {
                List<Song> songList = Transform.transformAlbumSongList(AcquireDataTool.getAlbumSongList(album.getId()));
                if (songList != null && songList.size() > 0) {
                    album.setName(songList.get(0).getSingerName());
                }
            }
            Contants.albumListMap.put(from, albumList);
            return albumList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/album/songs", method = RequestMethod.GET)
    public List<Song> getAlbumSongs(@RequestParam(value = "id") int specialId) {

        if(Contants.albumSongListMap != null && Contants.albumSongListMap.get(specialId) != null){
            return Contants.albumSongListMap.get(specialId);
        }

        try {
            List<Song> songList = Transform.transformAlbumSongList(AcquireDataTool.getAlbumSongList(specialId));
            if (songList != null && songList.size() > 0) {
                for (Song item : songList) {
                    Song song = Transform.detailResponse2Song(AcquireDataTool.getSongDetail(item.getHash()));
                    item.setImgUrl(song.getImgUrl());
                    item.setSingerName(song.getSingerName());
                    item.setSongName(song.getSongName());
                    item.setDuration(song.getDuration());
                }
                Contants.albumSongListMap.put(specialId, songList);
            }
            return songList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
