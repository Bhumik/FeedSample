package com.finalhints.mymandirdemo.view.viewholder

import android.view.View
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.callbacks.RecyclerViewItemProvider
import com.finalhints.mymandirdemo.widgets.ExoVideoView

/**
 * video view holder which extend base holder
 */
class FeedVideoItemHolder(itemView: View, mFeedItemActionCallBack: FeedItemsActionListener?, itemProvider: RecyclerViewItemProvider<FeedItem>)
    : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, itemProvider) {

    private val exoVideoVIew = itemView.findViewById<ExoVideoView>(R.id.exoVideoVIew)

    init {
    }

    override fun bindView(item: FeedItem) {
        super.bindView(item)
        exoVideoVIew.setFeedData(item.attachments?.get(0)!!)
    }
}