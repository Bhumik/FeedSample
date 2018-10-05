package com.finalhints.mymandirdemo.view.viewholder

import android.view.View
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.callbacks.RecyclerViewItemProvider
import com.finalhints.mymandirdemo.widgets.MultiImageView

/**
 * multi image view holder which extend base holder
 */
class FeedImageItemHolder(itemView: View, mFeedItemActionCallBack: FeedItemsActionListener?, itemProvider: RecyclerViewItemProvider<FeedItem>)
    : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, itemProvider) {

    val ivFeedImage = itemView.findViewById<MultiImageView>(R.id.ivFeedImage)

    init {
        ivFeedImage.setOnItemClickListener { position, _, _ -> mFeedItemActionCallBack?.onImageItemClick(adapterPosition, itemProvider.getItem(adapterPosition), position) }
    }

    init {
    }

    override fun bindView(item: FeedItem) {
        super.bindView(item)

        ivFeedImage.initData(item.attachments)
    }
}