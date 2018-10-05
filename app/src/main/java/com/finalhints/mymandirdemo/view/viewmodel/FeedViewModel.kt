package com.finalhints.mymandirdemo.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.network.NetworkListenerSE
import com.finalhints.mymandirdemo.network.RetrofitManager
import com.finalhints.mymandirdemo.network.getResponse
import com.finalhints.mymandirdemo.network.service.FeedApiService
import com.finalhints.mymandirdemo.utils.AppUtils
import com.finalhints.mymandirdemo.utils.InternetUtil


class FeedViewModel : ViewModel() {

    val feedItemList = MutableLiveData<ArrayList<FeedItem>>()
    val emptyState = MutableLiveData<AppUtils.EmptyState>()
    val errorDesc = MutableLiveData<String>()


    /**
     * method to get feed items from remote repository
     *
     * can be moved to repository
     */
    fun getFeedItems() {

        val mRetrofit = RetrofitManager.getRetrofit()
        val sObservable = mRetrofit.create(FeedApiService::class.java).getFeedItems()

        if (!InternetUtil.isInternetOn()) {
            emptyState.value = AppUtils.EmptyState.NO_NETWORK
            return
        }
        sObservable
                .doAfterTerminate {
                    //hide loading
                    emptyState.postValue(AppUtils.EmptyState.GONE)
                }
                .doOnSubscribe {
                    //show loading
                    emptyState.postValue(AppUtils.EmptyState.LOADING)
                }
                .getResponse(object : NetworkListenerSE<ArrayList<FeedItem>> {
                    override fun onSuccess(code: Int, pDescription: String, response: ArrayList<FeedItem>) {
                        feedItemList.value = response
                    }

                    override fun onError(code: Int, pDescription: String) {
                        errorDesc.value = pDescription
                    }
                })
    }
}