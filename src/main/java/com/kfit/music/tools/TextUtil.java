package com.kfit.music.tools;

import com.sun.istack.internal.Nullable;

public class TextUtil {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
