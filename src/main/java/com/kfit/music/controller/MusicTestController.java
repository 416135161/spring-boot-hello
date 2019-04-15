package com.kfit.music.controller;

import com.kfit.crawl.bean.AlbumList.Info;
import com.kfit.crawl.tools.AcquireDataTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicTestController {

    @RequestMapping(value = "/albums/test", method = RequestMethod.GET)
    public List<Info> getAlbumsTest(@RequestParam(value = "pageSize", required = false, defaultValue = "30") Integer pageSize) {
        return AcquireDataTool.getAlbumList(21, pageSize);
    }

    @RequestMapping(value = "/albums/songs/test", method = RequestMethod.GET)
    public List<com.kfit.crawl.bean.AlbumSongList.Info> getAlbumsSongTest() {
        return AcquireDataTool.getAlbumSongList(635177, 1);
    }
}
