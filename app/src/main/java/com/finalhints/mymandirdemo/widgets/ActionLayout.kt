package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.finalhints.mymandirdemo.datamodel.FeedItem
import com.google.android.material.button.MaterialButton


class ActionLayout : FrameLayout {

    /*view of layout*/
    private lateinit var tvActionDownload: MaterialButton
    private lateinit var tvActionLike: MaterialButton
    private lateinit var tvActionComments: MaterialButton
    private lateinit var tvActionWhatsApp: MaterialButton
    private lateinit var tvActionShare: MaterialButton


    private var mFeedItem: FeedItem? = null

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
        val view = LayoutInflater.from(context).inflate(R.layout.layout_action, this, true)

        tvActionDownload = view.findViewById<View>(R.id.tvActionDownload) as MaterialButton
        tvActionLike = view.findViewById<View>(R.id.tvActionLike) as MaterialButton
        tvActionComments = view.findViewById<View>(R.id.tvActionComments) as MaterialButton
        tvActionWhatsApp = view.findViewById<View>(R.id.tvActionWhatsApp) as MaterialButton
        tvActionShare = view.findViewById<View>(R.id.tvActionShare) as MaterialButton

        tvActionDownload.setOnClickListener { onActionListener?.onDownloadAction(mFeedItem) }
        tvActionLike.setOnClickListener {
            tvActionLike.isActivated = !tvActionLike.isActivated
            onActionListener?.onLikeChangeAction(mFeedItem, tvActionLike.isActivated)
        }
        tvActionComments.setOnClickListener { onActionListener?.onAddCommentAction(mFeedItem) }
        tvActionWhatsApp.setOnClickListener { onActionListener?.onWhatsappShareAction(mFeedItem) }
        tvActionShare.setOnClickListener { onActionListener?.onShareAction(mFeedItem) }
    }

    public fun initData(attachment: FeedItem?) {
        mFeedItem = attachment

//        tvActionDownload.text = mFeedItem.count
        tvActionComments.text = mFeedItem?.commentCount?.toString() ?: "-"
        tvActionShare.text = mFeedItem?.shareCount?.toString() ?: "-"
        tvActionLike.text = mFeedItem?.likeCount?.toString() ?: "-"
        tvActionWhatsApp.text = mFeedItem?.shareCount?.toString() ?: "-"
    }

    var onActionListener: OnHeaderActionListener? = null

    interface OnHeaderActionListener {
        fun onShareAction(feedItem: FeedItem?)
        fun onDownloadAction(feedItem: FeedItem?)
        fun onAddCommentAction(feedItem: FeedItem?)
        fun onWhatsappShareAction(feedItem: FeedItem?)
        fun onLikeChangeAction(feedItem: FeedItem?, isLikes: Boolean)
    }
}

