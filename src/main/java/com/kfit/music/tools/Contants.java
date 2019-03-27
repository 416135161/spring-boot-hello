package com.kfit.music.tools;

import com.kfit.music.bean.Album;
import com.kfit.music.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contants {

    /**
     * 资源来源-按国家分
     */
    public static final int FROM_US = 0;
    public static final int FROM_JAPAN = 1;

    public static HashMap<Integer, List<Album>> albumListMap = new HashMap<>();
    public static HashMap<Integer, List<Song>> albumSongListMap = new HashMap<>();
}
