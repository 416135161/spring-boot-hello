package com.kfit.crawl.api;

import com.sun.javafx.tools.packager.Log;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpUtil {

    public static MusicApi getApiService(String host, Interceptor interceptor) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(host);
        OkHttpClient.Builder httpBuild = new OkHttpClient.Builder();

        if (interceptor != null) {
            httpBuild.addInterceptor(interceptor);

        }
        //添加日志打印拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String s) {
                Log.info(s);
                System.out.print(s);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpBuild.addInterceptor(logInterceptor);
        httpBuild.addInterceptor(new FilterInterceptor());

        builder.client(httpBuild.build());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit client = builder.build();
        MusicApi musicApi = client.create(MusicApi.class);
        return musicApi;
    }

    public static class FilterInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这个是请求的url，也就是咱们前面配置的baseUrl
            String url = request.url().toString();
            //这个是请求方法
            String method = request.method();
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            response = decrypt(response);
            return response;
        }

        public Response decrypt(Response response) throws IOException {
            if (response.isSuccessful()) {
                //the response data
                ResponseBody body = response.body();
                BufferedSource source = body.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Charset charset = Charset.defaultCharset();
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                String string = buffer.clone().readString(charset);

                String bodyString = null;
                try {
                    bodyString = string.replace("<!--KG_TAG_RES_START-->", "").replace("<!--KG_TAG_RES_END-->", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ResponseBody responseBody = ResponseBody.create(contentType, bodyString);
                response = response.newBuilder().body(responseBody).build();
            }
            return response;
        }
    }


}
