package com.kfit.crawl.api;

import com.kfit.crawl.bean.AlbumInfo.AlbumInfoBean;
import com.kfit.crawl.bean.AlbumList.AlbumListBean;
import com.kfit.crawl.bean.AlbumSongList.AlbumSongListBean;
import com.kfit.crawl.bean.search.SearchResponseBean;
import com.kfit.crawl.bean.songDetail.SongDetailBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicApi {
    String HOST_COMMON = "http://mobilecdn.kugou.com";
    String HOST_GET_SONG = "http://www.kugou.com";


    /**
     * 酷狗搜歌
     *
     * @param keyword
     * @return
     */
    @GET("/api/v3/search/song?format=json&page=1&showtype=1")
    Call<SearchResponseBean> searchSong(@Query("keyword") String keyword, @Query("pagesize") int pagesize);

    /**
     * 获取歌曲播放地址
     *
     * @param hash
     * @return
     */
    @GET("/yy/index.php?r=play/getdata")
    Call<SongDetailBean> getSongDetail(@Query("hash") String hash);


    //

    /**
     * http://mobilecdn.kugou.com/api/v3/tag/info?&apiver=2&id=27
     *
     * @param id 获取专辑信息（id=27 英语  id=29日语）
     * @return
     */
    @GET("/api/v3/tag/info?&apiver=2&id=27")
    Call<AlbumInfoBean> getAlbumInfo(@Query("id") int id);

    /**
     * 获取专辑列表 （）
     * http://mobilecdn.kugou.com/api/v3/tag/specialList?tagid=21&plat=0&sort=2&ugc=1&id=29&page=1&pagesize=30
     *
     * @param tagid 从上面接口获取special_tag_id
     * @return
     */
    @GET("api/v3/tag/specialList?plat=0&sort=2&ugc=1&id=29&page=1")
    Call<AlbumListBean> getAlbumList(@Query("tagid") int tagid, @Query("pagesize") int pagesize);

    /**
     * 获取专辑下的歌曲列表
     * http://mobilecdn.kugou.com/api/v3/special/song?with_cover=1&plat=0&specialid=622977&page=1&pagesize=-1&area_code=1&version=9156&with_res_tag=1
     *
     * @param specialid 从上面接口获取specialid
     * @return
     */
    @GET("/api/v3/special/song?with_cover=1&plat=0&page=1&pagesize=-1&area_code=1&version=9156&with_res_tag=1")
    Call<AlbumSongListBean> getAlbumSongList(@Query("specialid") int specialid);
}
