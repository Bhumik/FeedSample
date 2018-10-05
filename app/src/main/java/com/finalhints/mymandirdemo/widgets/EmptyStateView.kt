package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.layout_emptystateview.view.*


class EmptyStateView : RelativeLayout {


    private var mSTRTitle: String? = null
    private var mSTRMessage: String? = null
    private var imageRes: Int = 0

    private lateinit var mImg: ImageView
    private lateinit var mTvTitle: TextView
    private lateinit var mTvMessage: TextView
    private lateinit var mRlNoData: RelativeLayout
    private lateinit var btnAction: MaterialButton

    internal var mActionListener: EmptyStateActionListener? = null

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
        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyStateView, 0, 0)

        mSTRTitle = a.getString(R.styleable.EmptyStateView_esvTitle)
        mSTRMessage = a.getString(R.styleable.EmptyStateView_esvMessage)
        imageRes = a.getResourceId(R.styleable.EmptyStateView_esvImage, android.R.drawable.ic_delete)

        a.recycle()
    }


    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_emptystateview, this, true)
        mImg = view.findViewById(R.id.img)
        mTvTitle = view.findViewById(R.id.tvTitle)
        mTvMessage = view.findViewById(R.id.tvMessage)
        mRlNoData = view.findViewById(R.id.rlNoData)
        btnAction = view.findViewById(R.id.btnAction)

        btnAction.setOnClickListener {
            if (mActionListener != null) {
                mActionListener!!.onActionClick()
            }
        }
        btnAction.visibility = View.GONE
        setData()
    }

    fun initView(title: String, message: String, image: Int) {
        mSTRTitle = title
        mSTRMessage = message
        imageRes = image
        btnAction.visibility = View.GONE
        progressBar.isVisible = false
        setData()
    }

    fun initView(title: String, message: String, image: Int, btnText: String, pListener: EmptyStateActionListener) {
        mSTRTitle = title
        mSTRMessage = message
        imageRes = image
        btnAction.text = btnText
        btnAction.visibility = View.VISIBLE
        mActionListener = pListener
        progressBar.isVisible = false
        setData()
    }

    fun initViewAsLoading(subTitle: String) {
        mSTRTitle = "Loading"
        mSTRMessage = subTitle
        mImg.isVisible = false
        btnAction.isVisible = false
        progressBar.isVisible = true
        setData()
    }

    private fun setData() {
        setTitle()
        setMessage()
        setImage()
    }

    private fun setImage() {
        if (imageRes != 0) {
            //            rpv.setVectorDrawable(imageRes);
            mImg.setImageResource(imageRes)
        }
    }

    private fun setMessage() {
        if (mSTRMessage != null) {
            mTvMessage.text = mSTRMessage
        }
    }

    private fun setTitle() {
        if (mSTRTitle != null) {
            mTvTitle.text = mSTRTitle
        }
    }

    fun setTitle(mSTRTitle: String) {
        this.mSTRTitle = mSTRTitle
        setTitle()
    }

    fun setMessage(mSTRMessage: String) {
        this.mSTRMessage = mSTRMessage
        setMessage()
    }

    fun setImageRes(imageRes: Int) {
        this.imageRes = imageRes
        setImage()
    }


    interface EmptyStateActionListener {
        fun onActionClick()
    }


}

