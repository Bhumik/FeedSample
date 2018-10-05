package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.finalhints.mymandirdemo.utils.AppUtils


class FeedView : FrameLayout {

    /*view of layout*/
    //top header view of user
    lateinit var headerView: HeaderUserView0

    //title text of feed
    lateinit var tvFeedPrimaryText: TextView
    //description text of feed
    lateinit var tvFeedSecondaryText: TextView
    //multi image view to show all images of post
    lateinit var ivFeedImage: MultiImageView

    //exo audio player for audio feed
    lateinit var exoAudioView: ExoAudioView
    //exo video player for video feed
    lateinit var exoVideoVIew: ExoVideoView

    //action view of all action which can be done on feed
    lateinit var action_layout: ActionLayout0

    //bottom tag list
    lateinit var mTagList: TextView
    //bottom tag divider
    lateinit var mTagDivider: View


    /**
     * data variable with details of feed item
     */
    private var mFeedItem: FeedItem? = null


    /**
     * feed action listner to pass events to view
     */
    var mFeedActionListener: FeedActionListener? = null


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        loadAttrs(context, attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        loadAttrs(context, attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        loadAttrs(context, attrs)
        initView()
    }

    /**
     * Load Default Properties values if set from xml file.
     *
     * @param context context
     * @param attrs   AttributeSet
     */
    private fun loadAttrs(context: Context, attrs: AttributeSet) {}

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_feeditem, this, true)

        headerView = view.findViewById<HeaderUserView0>(R.id.clHeaderView)
        tvFeedPrimaryText = view.findViewById<TextView>(R.id.tvFeedPrimaryText)
        tvFeedSecondaryText = view.findViewById<TextView>(R.id.tvFeedSecondaryText)
        ivFeedImage = view.findViewById<MultiImageView>(R.id.ivFeedImage)
        exoVideoVIew = view.findViewById<ExoVideoView>(R.id.exoVideoVIew)
        exoAudioView = view.findViewById<ExoAudioView>(R.id.exoAudioVIew)
        action_layout = view.findViewById<ActionLayout0>(R.id.action_layout)
        mTagList = view.findViewById<TextView>(R.id.tagList)
        mTagDivider = view.findViewById<View>(R.id.tagDivider)
    }

    public fun initData(feedItem: FeedItem) {
        mFeedItem = feedItem

        headerView.initData(mFeedItem?.sender, mFeedItem?.createdAt, false)

        AppUtils.getCompatText(mFeedItem?.title?.trim() ?: "") { _, compactedText ->
            tvFeedPrimaryText.text = compactedText
        }
        AppUtils.getCompatText(mFeedItem?.text?.trim() ?: "") { _, compactedText ->
            tvFeedSecondaryText.text = compactedText
        }

        action_layout.initData(mFeedItem)

        val tagslist = feedItem.tags?.map { it -> "#${it.text}" }?.joinToString("  ") ?: ""
        mTagList.isVisible = !tagslist.isEmpty()
        mTagDivider.isVisible = !tagslist.isEmpty()
        mTagList.text = tagslist


        feedItem.attachments?.let {
            if (it.size == 1 && it[0].isAudio) {
                exoVideoVIew.isVisible = false
                ivFeedImage.isVisible = false

                exoAudioView.isVisible = true
                exoAudioView.setAudioUrl(it[0].url ?: "")
            } else if (it.size == 1 && it[0].isVideo) {
                exoAudioView.isVisible = false
                ivFeedImage.isVisible = false

                exoVideoVIew.isVisible = true
                exoVideoVIew.setFeedData(it[0])
            } else {
                exoVideoVIew.isVisible = false
                exoAudioView.isVisible = false
                ivFeedImage.isVisible = true
                ivFeedImage.initData(it)
            }
        }
    }

    interface FeedActionListener {
        fun onHeaderClick()
        fun onImagesClick()
        fun onLikeAction()
    }
}

