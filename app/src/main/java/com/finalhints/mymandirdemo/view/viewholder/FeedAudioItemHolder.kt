package com.finalhints.mymandirdemo.view.viewholder

import android.view.View
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.callbacks.RecyclerViewItemProvider
import com.finalhints.mymandirdemo.widgets.ExoAudioView

/**
 * audio view holder which extend base holder
 */
class FeedAudioItemHolder(itemView: View, mFeedItemActionCallBack: FeedItemsActionListener?, itemProvider: RecyclerViewItemProvider<FeedItem>)
    : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, itemProvider) {

    private val exoAudioView = itemView.findViewById<ExoAudioView>(R.id.exoAudioView)

    init {

    }

    override fun bindView(item: FeedItem) {
        super.bindView(item)

        exoAudioView.setAudioUrl(item.attachments?.get(0)?.url ?: "")
        exoAudioView.setPreviewUrl(item.attachments?.get(0)?.thumbnailUrl ?: "")
    }
}