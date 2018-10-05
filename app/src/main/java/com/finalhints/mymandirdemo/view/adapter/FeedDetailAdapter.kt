package com.finalhints.mymandirdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalhints.mymandirdemo.datamodel.Attachment
import kotlinx.android.synthetic.main.adapter_feed_detail_item.view.*

class FeedDetailAdapter(
        private val mContext: Context,
        private val mEntityList: ArrayList<Attachment>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentItemHolder {
        val sView = LayoutInflater.from(mContext).inflate(viewType, parent, false)
        return AttachmentItemHolder(sView)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.adapter_feed_detail_item
    }

    override fun getItemCount(): Int {
        return mEntityList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AttachmentItemHolder) {
            holder.bindView(getItem(position))
        }
    }

    private fun getItem(position: Int): Attachment {
        return mEntityList[position]
    }


    inner class AttachmentItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFeedDetailImage = itemView.ivFeedDetailImage

        fun bindView(item: Attachment) {
            ivFeedDetailImage.initData(item, true)
        }

        init {
            ivFeedDetailImage.setOnClickListener {
                if (getItem(adapterPosition).isVideo) {
                    mCallBack?.onVideoPlayClick(adapterPosition, getItem(adapterPosition))
                }
            }
        }
    }

    interface FeedDetailItemActionListener {
        fun onVideoPlayClick(adapterPosition: Int, item: Attachment)
    }

    var mCallBack: FeedDetailItemActionListener? = null

}