package com.finalhints.mymandirdemo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Bhumik Sapara
 */
public class OkHttpClientManager {
    private static OkHttpClientManager INSTANCE;
    private OkHttpClient mOkHttpClient;

    private OkHttpClientManager() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder sBuilder = new OkHttpClient().newBuilder();
            sBuilder.connectTimeout(10, TimeUnit.SECONDS);
            sBuilder.readTimeout(10, TimeUnit.SECONDS);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            sBuilder.addInterceptor(logging);

            mOkHttpClient = sBuilder.build();
        }
    }

    public static OkHttpClient getOkHttpInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OkHttpClientManager();
        }
        return INSTANCE.mOkHttpClient;
    }

    public static OkHttpClient getNewInstance() {
        OkHttpClient.Builder sBuilder = getOkHttpInstance().newBuilder();
        sBuilder.connectTimeout(10, TimeUnit.SECONDS);
        sBuilder.readTimeout(10, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        sBuilder.addInterceptor(logging);
        return sBuilder.build();
    }

    public static OkHttpClient getNewInstance(Interceptor pInterceptor) {
        OkHttpClient.Builder sBuilder = getOkHttpInstance().newBuilder();
        sBuilder.connectTimeout(10, TimeUnit.SECONDS);
        sBuilder.readTimeout(10, TimeUnit.SECONDS);
        sBuilder.addInterceptor(pInterceptor);

/*
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        sBuilder.addInterceptor(logging);
*/

        return sBuilder.build();
    }


    public static OkHttpClient.Builder getNewBuilderInstance() {
        return getOkHttpInstance().newBuilder();
    }

}
