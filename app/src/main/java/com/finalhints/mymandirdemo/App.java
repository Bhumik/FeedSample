package com.finalhints.mymandirdemo;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalhints.mymandirdemo.network.RetrofitManager;
import com.finalhints.mymandirdemo.utils.AppConstant;
import com.finalhints.mymandirdemo.utils.InternetUtil;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //initialize retrofit instance
        RetrofitManager.init(AppConstant.BASEURL, null, getJacksonConverterFactory(), RxJava2CallAdapterFactory.create());

        Fresco.initialize(this);
        //Fresco.initialize(sInstance, FrescoConfig.getImagePipelineConfig(sInstance));

        InternetUtil.INSTANCE.init(this);
    }


    private JacksonConverterFactory getJacksonConverterFactory() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        return JacksonConverterFactory.create(objectMapper);
    }
}
