package com.kfit.music.service;

import com.kfit.music.tools.Contants;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    public void saveImg(String hash, String img) {
        if (hash != null && !hash.isEmpty() && img != null && !img.isEmpty()) {
            Contants.songImgMap.put(hash, img);
        }
    }
}
