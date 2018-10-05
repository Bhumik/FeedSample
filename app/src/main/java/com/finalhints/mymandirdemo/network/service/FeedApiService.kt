package com.finalhints.mymandirdemo.network.service

import com.finalhints.mymandirdemo.datamodel.FeedItem
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Bhumik Sapara
 */
interface FeedApiService {
    /**
     * api to get feed items
     * http://staging.mymandir.com/dummy
     *
     */
    @GET("dummy")
    fun getFeedItems(): Observable<ArrayList<FeedItem>>

    fun getFeedItemss(): Single<ArrayList<FeedItem>>
}


