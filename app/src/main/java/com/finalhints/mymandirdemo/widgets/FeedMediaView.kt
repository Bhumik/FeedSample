package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.SimpleDraweeView
import com.finalhints.mymandirdemo.datamodel.Attachment


class FeedMediaView : RelativeLayout {


    private lateinit var mIvMediaView: SimpleDraweeView

    private lateinit var mIvIsVideo: AppCompatImageView

    private var mIsVideo: Boolean = false


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
    private fun loadAttrs(context: Context, attrs: AttributeSet) {
    }

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_widget_feedmediaview, this, true)
        mIvMediaView = view.findViewById(R.id.ivSDVmediaView);
        mIvIsVideo = view.findViewById(R.id.ivIsVideo);
    }

    public fun initData(attachment: Attachment) {
        initData(attachment, false)
    }

    public fun initData(attachment: Attachment, keepAspectRatio: Boolean = false, isMultiImage: Boolean = false) {
        mIsVideo = attachment.isVideo

        mIvIsVideo.isVisible = mIsVideo
        val url: String? = if (mIsVideo) {
            attachment.thumbnailUrl
        } else {

            /**
             * TODO
             * we can also user Fresco Image pipleine to load optimised url progressively
             */

/*
            if(!attachment.thumbnailUrl.isNullOrEmpty()){
                attachment.thumbnailUrl
            }else
*/
            if (!attachment.downsampledUrl.isNullOrEmpty()) {
                attachment.downsampledUrl
            } else if (!attachment.mediumUrl.isNullOrEmpty()) {
                attachment.mediumUrl
            } else {
                attachment.url
            }
        }


        if (keepAspectRatio) {
            val aspectRatio = (attachment.metadata.width.toFloat() / attachment.metadata.height)
            mIvMediaView.aspectRatio = aspectRatio

            this.layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
            this.mIvMediaView.layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
        }

        mIvMediaView.hierarchy.actualImageScaleType = if (isMultiImage) {
            ScalingUtils.ScaleType.CENTER_CROP
        } else {
            ScalingUtils.ScaleType.FIT_CENTER
        }
        mIvMediaView.setImageURI(Uri.parse(url), this)
    }
}

