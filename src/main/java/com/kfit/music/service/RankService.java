package com.kfit.music.service;

import com.kfit.crawl.tools.AcquireDataTool;
import com.kfit.music.bean.Song;
import com.kfit.music.tools.Contants;
import com.kfit.music.tools.SongTool;
import com.kfit.music.tools.Transform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankService {
    public List<Song> getRankSongs(int rankId) {
        if (Contants.rankSongListMap != null && Contants.rankSongListMap.get(rankId) != null) {
            return Contants.rankSongListMap.get(rankId);
        }

        getRemoteRankSongs(rankId);

        if (Contants.rankSongListMap != null && Contants.rankSongListMap.get(rankId) != null) {
            return Contants.rankSongListMap.get(rankId);
        } else {
            return new ArrayList<>();
        }
    }

    public void getRemoteRankSongs(int rankId) {
        try {
            List<com.kfit.crawl.bean.rankList.Vols> rankList = AcquireDataTool.getRandList(rankId);
            if (rankList == null || rankList.size() == 0) {
                throw new Exception();
            }
            List<Song> songList = Transform.transformRandSongList(AcquireDataTool.getRandSongList(rankId, rankList.get(0).getVolid()));
            if (SongTool.reassemblySong(songList)) {
                Contants.rankSongListMap.put(rankId, songList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
