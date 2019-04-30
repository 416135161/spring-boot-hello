package com.kfit.music.tools;

import java.util.Collections;
import java.util.List;


public final class SortUtil {
    private SortUtil() {
    }


    public static <T> List shuffle(List<T> list) {
        Collections.shuffle(list);
        return list;
    }
}
