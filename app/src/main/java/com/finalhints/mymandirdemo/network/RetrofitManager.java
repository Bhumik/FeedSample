package com.finalhints.mymandirdemo.network;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Bhumik Sapara
 */
public class RetrofitManager {
    private static String DEFAULT_RETROFIT = "DEFAULT_RETROFIT";
    private static Retrofit mRetrofit;
    private static HashMap<String, Retrofit> mRetrofitHashMap = new HashMap<>();

    private RetrofitManager() {
    }


    public static void init(String pBaseUrl) {
        init(DEFAULT_RETROFIT, pBaseUrl, null, null, null);
    }

    public static void init(String pBaseUrl, OkHttpClient pOkHttpClient) {
        init(DEFAULT_RETROFIT, pBaseUrl, pOkHttpClient, null, null);
    }

    public static void init(String pBaseUrl, OkHttpClient pOkHttpClient, Converter.Factory pConverterFactory, CallAdapter.Factory pCallAdapterFactory) {
        init(DEFAULT_RETROFIT, pBaseUrl, pOkHttpClient, pConverterFactory, pCallAdapterFactory);
    }

    public static void init(String pTag, String pBaseUrl, OkHttpClient pOkHttpClient, Converter.Factory pConverterFactory, CallAdapter.Factory pCallAdapterFactory) {
        if (mRetrofitHashMap.containsKey(pTag)) {
            return;
        }
        mRetrofit = makeNewRetrofit(pBaseUrl, pOkHttpClient, pConverterFactory, pCallAdapterFactory);
        mRetrofitHashMap.put(pTag, mRetrofit);
    }


    public static Retrofit makeNewRetrofit(String pBaseUrl, OkHttpClient pOkHttpClient, Converter.Factory pConverterFactory, CallAdapter.Factory pCallAdapterFactory) {
        if (pOkHttpClient == null) {
            pOkHttpClient = OkHttpClientManager.getOkHttpInstance();
        }

        Retrofit.Builder sBuilder = new Retrofit.Builder();

        sBuilder.client(pOkHttpClient);
        sBuilder.baseUrl(pBaseUrl);
        if (pConverterFactory != null) {
            sBuilder.addConverterFactory(pConverterFactory);
        }
        if (pCallAdapterFactory != null) {
            sBuilder.addCallAdapterFactory(pCallAdapterFactory);
        }
        return sBuilder.build();
    }


    public static Retrofit getRetrofit() {
        if (!mRetrofitHashMap.containsKey(DEFAULT_RETROFIT)) {
            throw new ExceptionInInitializerError("retrofit not initialized");
        }
        return mRetrofitHashMap.get(DEFAULT_RETROFIT);
    }


    public static Retrofit getRetrofit(String pTag) {
        if (!mRetrofitHashMap.containsKey(pTag)) {
            throw new ExceptionInInitializerError("specified retrofit[" + pTag + "] not initialized");
        }
        return mRetrofitHashMap.get(pTag);
    }


    public static void destroyRetrofit(String pTag) {
        if (mRetrofitHashMap.containsKey(pTag)) {
            mRetrofitHashMap.remove(pTag);
        }
    }

}
