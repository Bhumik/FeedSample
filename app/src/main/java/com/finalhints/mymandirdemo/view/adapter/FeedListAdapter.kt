package com.finalhints.mymandirdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.view.callbacks.FeedItemsActionListener
import com.finalhints.mymandirdemo.view.callbacks.RecyclerViewItemProvider
import com.finalhints.mymandirdemo.view.viewholder.FeedAudioItemHolder
import com.finalhints.mymandirdemo.view.viewholder.FeedImageItemHolder
import com.finalhints.mymandirdemo.view.viewholder.FeedVideoItemHolder

class FeedListAdapter(
        private val mContext: Context,
        private val mEntityList: ArrayList<FeedItem>,
        private val mFeedItemActionCallBack: FeedItemsActionListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerViewItemProvider<FeedItem> {

    private val VIEW_TYPE_AUDIO = R.layout.adapter_feed_item_audio
    private val VIEW_TYPE_VIDEO = R.layout.adapter_feed_item_video
    private val VIEW_TYPE_MULTIIMAGE = R.layout.adapter_feed_item_photo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val sView = LayoutInflater.from(mContext).inflate(viewType, parent, false)
        if (viewType == VIEW_TYPE_VIDEO) {
            return FeedVideoItemHolder(sView, mFeedItemActionCallBack, this)
        }
        if (viewType == VIEW_TYPE_AUDIO) {
            return FeedAudioItemHolder(sView, mFeedItemActionCallBack, this)
        }
        return FeedImageItemHolder(sView, mFeedItemActionCallBack, this)
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).attachments?.size == 1 && (getItem(position).attachments?.get(0)?.isVideo == true)) {
            return VIEW_TYPE_VIDEO
        }
        if (getItem(position).attachments?.size == 1 && (getItem(position).attachments?.get(0)?.isAudio == true)) {
            return VIEW_TYPE_AUDIO
        }
        return VIEW_TYPE_MULTIIMAGE
    }

    override fun getItemCount(): Int {
        return mEntityList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedImageItemHolder) {
            holder.bindView(getItem(position))
        }
        if (holder is FeedVideoItemHolder) {
            holder.bindView(getItem(position))
        }
        if (holder is FeedAudioItemHolder) {
            holder.bindView(getItem(position))
        }
    }

    override fun getItem(position: Int): FeedItem {
        return mEntityList[position]
    }

/*
    inner class FeedImageItemHolder(itemView: View) : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, this) {
        val ivFeedImage = itemView.findViewById<MultiImageView>(R.id.ivFeedImage)

        init {
            ivFeedImage.setOnItemClickListener { position, _, _ -> mFeedItemActionCallBack?.onImageItemClick(adapterPosition, getItem(adapterPosition), position) }
        }

        override fun bindView(item: FeedItem) {
            super.bindView(item)

            ivFeedImage.initData(item.attachments)
        }
    }
*/

    /**
     * video view holder which extend base holder
     */
/*
    inner class FeedVideoItemHolder(itemView: View) : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, this) {
        private val exoVideoVIew = itemView.findViewById<ExoVideoView>(R.id.exoVideoVIew)

        override fun bindView(item: FeedItem) {
            super.bindView(item)
//            exoVideoVIew.setVideoUrl(item.attachments?.get(0)?.url ?: "")
            exoVideoVIew.setFeedData(item.attachments?.get(0)!!)
        }
    }
*/

    /**
     * audio view holder which extend base holder
     */
/*
    inner class FeedAudioItemHolder(itemView: View) : FeedBaseItemHolder(itemView, mFeedItemActionCallBack, this) {
        private val exoAudioView = itemView.findViewById<ExoAudioView>(R.id.exoAudioView)
        override fun bindView(item: FeedItem) {
            super.bindView(item)

            exoAudioView.setAudioUrl(item.attachments?.get(0)?.url ?: "")
            exoAudioView.setPreviewUrl(item.attachments?.get(0)?.thumbnailUrl ?: "")
        }
    }
*/
}