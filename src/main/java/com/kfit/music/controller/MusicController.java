package com.kfit.music.controller;

import com.kfit.crawl.tools.AcquireDataTool;
import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;
import com.kfit.music.service.AlbumService;
import com.kfit.music.service.EuropeService;
import com.kfit.music.service.RankService;
import com.kfit.music.tools.Contants;
import com.kfit.music.tools.Transform;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MusicController {

    @Resource
    private RankService rankService;

    @Resource
    private AlbumService albumService;

    @Resource
    private EuropeService europeService;


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
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {
        return albumService.getAlbums(from, pageSize);
    }

    @RequestMapping(value = "/album/songs", method = RequestMethod.GET)
    public List<Song> getAlbumSongs(@RequestParam(value = "id") int specialId) {
        return albumService.getAlbumSongs(specialId);
    }

    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public List<Song> getHotSongs(@RequestParam(value = "from", required = false, defaultValue = "0") int from) {
        int rankId;
        if (from == Contants.FROM_US) {
            rankId = Contants.US_HOT;
        } else if (from == Contants.FROM_JAPAN) {
            rankId = Contants.JAPAN_HOT;
        } else {
            return new ArrayList<>();
        }
        return rankService.getRankSongs(rankId);
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public List<Song> getNewSongs(@RequestParam(value = "from", required = false, defaultValue = "0") int from) {
        int rankId;
        if (from == Contants.FROM_US) {
            rankId = Contants.US_NEW;
        } else if (from == Contants.FROM_JAPAN) {
            rankId = Contants.JAPAN_NEW;
        } else {
            return new ArrayList<>();
        }
        return rankService.getRankSongs(rankId);
    }

    @RequestMapping(value = "europe/albums", method = RequestMethod.GET)
    public List<Album> getEuropeAlbums(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {
        return europeService.getAlbums(from, pageSize);
    }

    @RequestMapping(value = "europe/album/songs", method = RequestMethod.GET)
    public List<Song> getEuropeAlbumSongs(@RequestParam(value = "id") int id) {
        return europeService.getAlbumSongs(id);
    }


}
