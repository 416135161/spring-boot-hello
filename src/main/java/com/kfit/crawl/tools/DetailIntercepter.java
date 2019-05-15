package com.kfit.crawl.tools;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DetailIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
         // Header参数
            Request.Builder builder = request.newBuilder();
            builder.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,im" +
                    "age/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//            builder.addHeader("Accept-Encoding","gzip, deflate");
            builder.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            builder.addHeader("Cache-Control","max-age=0");
            builder.addHeader("Connection","keep-alive");
            builder.addHeader("User-agent", "KugouMusic/2.6.6 (Mac OS X Version 10.13.6 (Build 17G65))");
            builder.addHeader("Cookie", "__SDID=210b942ed44e6f0b; kg_mid=1c99e87133cb55f99e0faf3ba14937d8; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; kg_dfid=3dgv9f4CEGKF0BgEdl4dD7CG; UM_distinctid=16a6d11effe4a6-01d7d4cf9fb798-37617f05-13c680-16a6d11f000815; CNZZDATA3668490=cnzz_eid%3D977588826-1556608051-%26ntime%3D1556608051; Hm_lvt_aedee6983d4cfc62f509129360d6bb3d=1557023464,1557024476,1557414201,1557805539; ACK_SERVER_10015=%7B%22list%22%3A%5B%5B%22gzlogin-user.kugou.com%22%5D%5D%7D;" +
                    " kg_dfid_collect=d41d8cd98f00b204e9800998ecf8427e; Hm_lpvt_aedee6983d4cfc62f509129360d6bb3d=1557900303");
            builder.addHeader("Host", "www.kugou.com");
            builder.addHeader("If-Modified-Since", "Wed, 15 May 2019 06:14:47 GMT");
            builder.addHeader("Upgrade-Insecure-Requests", "1");
            builder.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            request = builder.build();
            return chain.proceed(request);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
