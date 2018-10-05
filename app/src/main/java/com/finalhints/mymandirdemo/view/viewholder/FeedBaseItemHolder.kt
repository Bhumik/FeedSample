package com.finalhints.mymandirdemo.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.utils.AppUtils
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.callbacks.RecyclerViewItemProvider
import com.finalhints.mymandirdemo.widgets.ActionLayout
import com.finalhints.mymandirdemo.widgets.HeaderUserView

open class FeedBaseItemHolder(itemView: View, mFeedItemActionCallBack: FeedItemsActionListener? = null, itemProvider: RecyclerViewItemProvider<FeedItem>) : RecyclerView.ViewHolder(itemView) {
    public val headerView = itemView.findViewById<HeaderUserView>(R.id.clHeaderView)

    val tvFeedPrimaryText = itemView.findViewById<TextView>(R.id.tvFeedPrimaryText)
    val tvFeedSecondaryText = itemView.findViewById<TextView>(R.id.tvFeedSecondaryText)

    val action_layout = itemView.findViewById<ActionLayout>(R.id.action_layout)

    val mTvTagList = itemView.findViewById<TextView>(R.id.tagList)
    val mTvTagDivider = itemView.findViewById<View>(R.id.tagDivider)

    init {
        headerView.setOnClickListener { mFeedItemActionCallBack?.onHeaderClick(adapterPosition, itemProvider.getItem(adapterPosition)) }
        tvFeedPrimaryText.setOnClickListener { mFeedItemActionCallBack?.onImageItemClick(adapterPosition, itemProvider.getItem(adapterPosition), 0) }
        tvFeedSecondaryText.setOnClickListener { mFeedItemActionCallBack?.onImageItemClick(adapterPosition, itemProvider.getItem(adapterPosition), 0) }
    }

    open fun bindView(item: FeedItem) {

        headerView.initData(item.sender, item.createdAt)

        AppUtils.getCompatText(item.title ?: "") { _, compactedText ->
            tvFeedPrimaryText.text = compactedText
        }
        AppUtils.getCompatText(item.text ?: "") { _, compactedText ->
            tvFeedSecondaryText.text = compactedText
        }

        headerView.initData(item.sender, item.createdAt)

        action_layout.initData(item)

        val tagslist = item.tags?.map { it -> "#${it.text}" }?.joinToString("  ") ?: ""
        mTvTagList.isVisible = !tagslist.isEmpty()
        mTvTagDivider.isVisible = !tagslist.isEmpty()
        mTvTagList.text = tagslist

    }
}