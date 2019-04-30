package com.kfit.music.tools;

import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contants {

    /**
     * 获取新歌榜期数 rankid =（31312日本新歌）（31310欧美新歌）（4681美国热歌榜）（4673日本热歌榜）
     */
    public static final int JAPAN_NEW = 31312;
    public static final int US_NEW = 31310;
    public static final int JAPAN_HOT = 4673;
    public static final int US_HOT = 4681;

    /**
     * 资源来源-按国家分
     */
    public static final int FROM_US = 0;
    public static final int FROM_JAPAN = 1;

    /**
     * 欧美资源分类
     */
    public static final int EUROP_POP = 1051;
    public static final int EUROP_COUNTRY = 1065;
    public static final int EUROP_ELECTRONIC = 1053;
    public static final int EUROP_ROCK = 1055;

    public static HashMap<Integer, List<Album>> albumListMap = new HashMap<>();
    public static HashMap<Integer, List<Song>> albumSongListMap = new HashMap<>();

    public static HashMap<Integer, List<Song>> rankSongListMap = new HashMap<>();


    public static HashMap<Integer, List<Album>> europeAlbumListMap = new HashMap<>();
    public static HashMap<Integer, List<Song>> europeAlbumSongListMap = new HashMap<>();
}
