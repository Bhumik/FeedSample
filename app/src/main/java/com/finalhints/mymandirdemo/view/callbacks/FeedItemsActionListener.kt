package com.finalhints.mymandirdemo.view.callbacks

import com.finalhints.mymandirdemo.datamodel.FeedItem


interface FeedItemsActionListener {
    fun onHeaderClick(adapterPosition: Int, item: FeedItem)
    fun onImageItemClick(adapterPosition: Int, item: FeedItem, itemPosition: Int)
    fun onTextReadMoreClick(adapterPosition: Int, item: FeedItem)
}
