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
public class EuropeService {

    public List<Album> getAlbums(int id, int pageSize) {
        if (Contants.europeAlbumListMap != null && Contants.europeAlbumListMap.get(id) != null) {
            return Contants.europeAlbumListMap.get(id);
        }
        getRemoteAlbums(id, pageSize);
        if (Contants.europeAlbumListMap != null && Contants.europeAlbumListMap.get(id) != null) {
            return Contants.europeAlbumListMap.get(id);
        } else {
            return new ArrayList<>();
        }

    }

    public void getRemoteAlbums(int id, int pageSize) {
        try {
            int albumId = AcquireDataTool.getEuroAlbumInfo(id);
            if (albumId == -1) {
                return;
            }
            List<Album> albumList = Transform.transformEuropeAlbumList(AcquireDataTool.getEuroAlbumList(albumId, pageSize));
            if (albumList == null || albumList.size() == 0) {
                return;
            }
            Contants.europeAlbumListMap.put(id, albumList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Song> getAlbumSongs(int albumId) {
        if (Contants.europeAlbumSongListMap != null && Contants.europeAlbumSongListMap.get(albumId) != null) {
            return Contants.europeAlbumSongListMap.get(albumId);
        }

        getRemoteAlbumSongs(albumId);
        if (Contants.europeAlbumSongListMap != null && Contants.europeAlbumSongListMap.get(albumId) != null) {
            return Contants.europeAlbumSongListMap.get(albumId);
        } else {
            return new ArrayList<>();
        }
    }

    public void getRemoteAlbumSongs(int albumId) {

        try {
            List<Song> songList = Transform.transformEuropeAlbumSongList(AcquireDataTool.getEuroAlbumSongList(albumId, 100));
            if (SongTool.reassemblySong(songList)) {
                Contants.europeAlbumSongListMap.put(albumId, songList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
