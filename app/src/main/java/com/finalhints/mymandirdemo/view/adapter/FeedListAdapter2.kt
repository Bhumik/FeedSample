package com.finalhints.mymandirdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.widgets.FeedView

class FeedListAdapter2(
        private val mContext: Context,
        private val mEntityList: ArrayList<FeedItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val sView = LayoutInflater.from(mContext).inflate(R.layout.adapter_feed_item2, parent, false)
        return FeedViewItemHolder(sView)
    }


    override fun getItemCount(): Int {
        return mEntityList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedViewItemHolder) {
            holder.bindView(getItem(position))
        }
    }

    private fun getItem(position: Int): FeedItem {
        return mEntityList[position]
    }

    /**
     * multi image view holder which extend base holder
     */
    inner class FeedViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feedView = itemView.findViewById<FeedView>(R.id.feed_view)

        fun bindView(item: FeedItem) {

            feedView.initData(item)
        }
    }

}