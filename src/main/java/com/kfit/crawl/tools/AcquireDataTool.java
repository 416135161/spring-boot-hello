package com.kfit.crawl.tools;

import com.kfit.crawl.api.HttpUtil;
import com.kfit.crawl.api.MusicApi;
import com.kfit.crawl.bean.AlbumInfo.AlbumInfoBean;
import com.kfit.crawl.bean.AlbumList.AlbumListBean;
import com.kfit.crawl.bean.AlbumList.Data;
import com.kfit.crawl.bean.AlbumList.Info;
import com.kfit.crawl.bean.AlbumSongList.AlbumSongListBean;
import com.kfit.crawl.bean.search.SearchResponseBean;
import com.kfit.crawl.bean.songDetail.SongDetailBean;
import com.kfit.music.tools.Contants;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class AcquireDataTool {


    static public SongDetailBean getSongDetail(String hash) {
        try {
            Call<SongDetailBean> call = HttpUtil.getApiService(MusicApi.HOST_GET_SONG, null).getSongDetail(hash);
            Response<SongDetailBean> response = call.execute();
            if (!response.isSuccessful()) {
                return null;
            } else {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public SearchResponseBean searchSongs(String keyword, int pageSize) {
        try {
            Call<SearchResponseBean> call = HttpUtil.getApiService(MusicApi.HOST_COMMON, null).searchSong(keyword, pageSize);
            Response<SearchResponseBean> response = call.execute();
            if (!response.isSuccessful()) {
                return null;
            } else {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public int getAlbumInfo(int from) {
        if (from == Contants.FROM_JAPAN) {
            from = 29;
        } else {
            from = 27;
        }
        int special_tag_id = -1;
        try {
            Call<AlbumInfoBean> callAlbumInfo = HttpUtil.getApiService(MusicApi.HOST_COMMON, null).getAlbumInfo(from);
            Response<AlbumInfoBean> responseAlbumInfo  = callAlbumInfo.execute();
            if (responseAlbumInfo.isSuccessful() && responseAlbumInfo.body() != null && responseAlbumInfo.body().getData() != null) {
                special_tag_id = responseAlbumInfo.body().getData().getSpecial_tag_id();
                System.out.println(special_tag_id);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return special_tag_id;
    }


    static public List<com.kfit.crawl.bean.AlbumList.Info> getAlbumList(int special_tag_id, int pageSize) {
        try {
            Call<AlbumListBean> call = HttpUtil.getApiService(MusicApi.HOST_COMMON, null).getAlbumList(special_tag_id, pageSize);
            Response<AlbumListBean> responseAlbumList = call.execute();
            if (responseAlbumList.isSuccessful() && responseAlbumList.body() != null && responseAlbumList.body().getData() != null) {
                Data data = responseAlbumList.body().getData();
                return data.getInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public List<com.kfit.crawl.bean.AlbumSongList.Info> getAlbumSongList(int specialId) {

        try {
            Call<AlbumSongListBean> callAlbumSongList = HttpUtil.getApiService(MusicApi.HOST_COMMON, null).getAlbumSongList(specialId);
            Response<AlbumSongListBean> responseAlbumSongList = callAlbumSongList.execute();
            if (responseAlbumSongList.isSuccessful() && responseAlbumSongList.body() != null && responseAlbumSongList.body().getData() != null) {
                return responseAlbumSongList.body().getData().getInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
