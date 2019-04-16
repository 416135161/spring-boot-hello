package com.kfit.music.scheduled;

import com.kfit.music.bean.Album;
import com.kfit.music.service.AlbumService;
import com.kfit.music.service.EuropeService;
import com.kfit.music.service.RankService;
import com.kfit.music.tools.Contants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Resource
    private RankService rankService;

    @Resource
    private AlbumService albumService;
    @Resource
    private EuropeService europeService;


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay = 5000, fixedRate = 6 * 60 * 60 * 1000)
    public void prepareData() {

        rankService.getRankSongs(Contants.US_HOT);
        rankService.getRankSongs(Contants.US_NEW);
        rankService.getRankSongs(Contants.JAPAN_NEW);
        rankService.getRankSongs(Contants.JAPAN_NEW);


        albumService.getRemoteAlbums(Contants.FROM_US, 50);
        if (Contants.albumListMap != null && Contants.albumListMap.get(Contants.FROM_US) != null) {
            for (Album album : Contants.albumListMap.get(Contants.FROM_US)) {
                albumService.getRemoteAlbumSongs(album.getId());
            }
        }

        albumService.getRemoteAlbums(Contants.FROM_JAPAN, 50);
        if (Contants.albumListMap != null && Contants.albumListMap.get(Contants.FROM_JAPAN) != null) {
            for (Album album : Contants.albumListMap.get(Contants.FROM_JAPAN)) {
                albumService.getRemoteAlbumSongs(album.getId());
            }
        }

        europeService.getRemoteAlbums(Contants.EUROP_POP, 50);
        if (Contants.europeAlbumListMap != null && Contants.europeAlbumListMap.get(Contants.EUROP_POP) != null) {
            for (Album album : Contants.europeAlbumListMap.get(Contants.EUROP_POP)) {
                europeService.getRemoteAlbumSongs(album.getId());
            }
        }

        europeService.getRemoteAlbums(Contants.EUROP_COUNTRY, 50);
        if (Contants.europeAlbumListMap != null && Contants.europeAlbumListMap.get(Contants.EUROP_COUNTRY) != null) {
            for (Album album : Contants.europeAlbumListMap.get(Contants.EUROP_COUNTRY)) {
                europeService.getRemoteAlbumSongs(album.getId());
            }
        }
    }


}