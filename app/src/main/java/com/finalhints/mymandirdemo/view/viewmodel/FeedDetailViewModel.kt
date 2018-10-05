package com.finalhints.mymandirdemo.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalhints.mymandirdemo.datamodel.FeedItem


class FeedDetailViewModel : ViewModel() {

    /**
     * live date of feed item received from main listing
     * */
    val feedItem = MutableLiveData<FeedItem>()

    /**
     * item click tracker which tells on which item user have clicked
     */
    var currentPos: Int = 0
}