package com.kfit.music.service;

import com.kfit.crawl.tools.AcquireDataTool;
import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;
import com.kfit.music.tools.Contants;
import com.kfit.music.tools.SongTool;
import com.kfit.music.tools.Transform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    public List<Album> getAlbums(int from, int pageSize) {
        if (Contants.albumListMap != null && Contants.albumListMap.get(from) != null) {
            return Contants.albumListMap.get(from);
        }
        getRemoteAlbums(from, pageSize);
        if (Contants.albumListMap != null && Contants.albumListMap.get(from) != null) {
            return Contants.albumListMap.get(from);
        } else {
            return new ArrayList<>();
        }

    }

    public void getRemoteAlbums(int from, int pageSize) {
        try {
            int special_tag_id = AcquireDataTool.getAlbumInfo(from);
            if (special_tag_id == -1) {
                return;
            }
            List<Album> albumList = Transform.transformAlbumList(AcquireDataTool.getAlbumList(special_tag_id, pageSize));
            if (albumList == null || albumList.size() == 0) {
                return;
            }
            for (Album album : albumList) {
                List<Song> songList = Transform.transformAlbumSongList(AcquireDataTool.getAlbumSongList(album.getId(), 1));
                if (songList != null && songList.size() > 0) {
                    album.setName(songList.get(0).getSingerName());
                }
            }
            Contants.albumListMap.put(from, albumList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Song> getAlbumSongs(int specialId) {
        if (Contants.albumSongListMap != null && Contants.albumSongListMap.get(specialId) != null) {
            return Contants.albumSongListMap.get(specialId);
        }

        getRemoteAlbumSongs(specialId);
        if (Contants.albumSongListMap != null && Contants.albumSongListMap.get(specialId) != null) {
            return Contants.albumSongListMap.get(specialId);
        } else {
            return new ArrayList<>();
        }
    }

    public void getRemoteAlbumSongs(int specialId) {

        try {
            List<Song> songList = Transform.transformAlbumSongList(AcquireDataTool.getAlbumSongList(specialId, -1));
            if (SongTool.reassemblySong(songList)) {
                Contants.albumSongListMap.put(specialId, songList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
