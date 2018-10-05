package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import com.finalhints.mymandirdemo.datamodel.FeedItem


class ActionLayout0 : FrameLayout {

    /*view of layout*/
    private lateinit var tvActionLike: AppCompatImageButton
    private lateinit var tvActionComments: AppCompatImageButton
    private lateinit var tvActionSave: AppCompatImageButton
    private lateinit var tvActionWhatsApp: AppCompatImageButton
    private lateinit var tvActionShare: AppCompatImageButton

    private lateinit var tvLikesCount: TextView
    private lateinit var tvCommentsCount: TextView
    private lateinit var tvShareCount: TextView


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
        val view = LayoutInflater.from(context).inflate(R.layout.layout_action2, this, true)

        tvActionLike = view.findViewById<AppCompatImageButton>(R.id.tvActionLike)
        tvActionComments = view.findViewById<AppCompatImageButton>(R.id.tvActionComments)
        tvActionWhatsApp = view.findViewById<AppCompatImageButton>(R.id.tvActionWhatsApp)
        tvActionSave = view.findViewById<AppCompatImageButton>(R.id.tvActionSave)
        tvActionShare = view.findViewById<AppCompatImageButton>(R.id.tvActionShare)
        tvLikesCount = view.findViewById<TextView>(R.id.tvLikesCount)
        tvCommentsCount = view.findViewById<TextView>(R.id.tvCommentsCount)
        tvShareCount = view.findViewById<TextView>(R.id.tvShareCount)

        tvActionSave.setOnClickListener { onActionListener?.onDownloadAction(mFeedItem) }
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

        tvCommentsCount.text = String.format(context.getString(R.string.count_comments), mFeedItem?.commentCount)
        tvLikesCount.text = String.format(context.getString(R.string.count_likes), mFeedItem?.likeCount)
        tvShareCount.text = String.format(context.getString(R.string.count_shares), mFeedItem?.shareCount)

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

