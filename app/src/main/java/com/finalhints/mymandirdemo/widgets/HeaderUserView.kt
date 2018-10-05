package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.facebook.drawee.view.SimpleDraweeView
import com.finalhints.mymandirdemo.datamodel.Sender
import com.finalhints.mymandirdemo.utils.AppUtils


class HeaderUserView : FrameLayout {

    /*view of layout*/
    private lateinit var mIvBack: ImageView
    private lateinit var mTvHeaderTitle: TextView
    private lateinit var mTvHeaderImageMini: SimpleDraweeView
    private lateinit var mTvHeaderCaption: TextView


    private var mSender: Sender? = null

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
        val view = LayoutInflater.from(context).inflate(R.layout.layout_header, this, true)
        mIvBack = view.findViewById<View>(R.id.ivBack) as ImageView
        mTvHeaderTitle = view.findViewById<View>(R.id.tvHeaderTitle) as TextView
        mTvHeaderImageMini = view.findViewById<View>(R.id.tvHeaderImageMini) as SimpleDraweeView
        mTvHeaderCaption = view.findViewById<View>(R.id.tvHeaderCaption) as TextView

        mIvBack.setOnClickListener { onHeaderActionListener?.onBackArrowClick() }
    }

    public fun initData(sender: Sender?, feedTime: Long?, showBackArrow: Boolean = false) {
        mSender = sender
        mIvBack.isVisible = showBackArrow
        mTvHeaderTitle.text = mSender?.name ?: "Unknown"
        mTvHeaderCaption.text = AppUtils.timeAgoInWords(feedTime)
        mTvHeaderImageMini.setImageURI(mSender?.microThumbnailUrl ?: "")
    }

    var onHeaderActionListener: OnHeaderActionListener? = null

    interface OnHeaderActionListener {
        fun onBackArrowClick()
        fun onHeaderBarClick()
    }
}

