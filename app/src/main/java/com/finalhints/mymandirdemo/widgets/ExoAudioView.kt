package com.finalhints.mymandirdemo.widgets

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.facebook.drawee.view.SimpleDraweeView
import com.finalhints.mymandirdemo.utils.AppUtils
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory


class ExoAudioView : FrameLayout {

    /*view of layout*/
    private lateinit var mPlayerView: PlayerView
    private lateinit var mIvAudioImage: SimpleDraweeView

    private var mPlayer: SimpleExoPlayer? = null


    /**
     * media source
     */
    private var mMediaSource: ExtractorMediaSource? = null

    /**
     * variable pointing to audio thumbnail to play
     */
    private var mPreviewUrl: String = "https://img5.mymandir.com/46e6ede0-c3ac-11e7-af60-d90cc8d0e249.png"

    /**
     * variable pointing to audio url to play
     */
    private var mAudioUrl: String = "http://www.largesound.com/ashborytour/sound/brobob.mp3"

    /**
     * to check if playerview is already prepared with mediaplayer
     */
    private var isPrepared: Boolean = false

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
        val view = LayoutInflater.from(context).inflate(R.layout.layout_widget_exoaudioview, this, true)
        mPlayerView = view.findViewById<View>(R.id.exoPlayerView) as PlayerView
        mIvAudioImage = view.findViewById<View>(R.id.ivAudioImage) as SimpleDraweeView
        val exo_play = view.findViewById<ImageButton>(R.id.exo_play)

        mPlayerView.controllerHideOnTouch = false
        mPlayerView.controllerShowTimeoutMs = 0

        exo_play.setOnClickListener {
            initializePlayer()
        }
        initPlayerUI()

        setPreviewUrl(mPreviewUrl)
    }

    /**
     * set video url to play in playerview
     */
    public fun setAudioUrl(audioUrl: String) {
        mAudioUrl = audioUrl
    }

    /**
     * set video url to play in playerview
     */
    public fun setPreviewUrl(previewUrl: String) {
        mPreviewUrl = previewUrl

        mIvAudioImage.setImageURI(Uri.parse(mPreviewUrl), context)
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        releasePlayer()
    }


    /**
     * release player to free resources and coder
     */
    private fun releasePlayer() {
        if (!isPrepared) {
            return
        }

        isPrepared = false
        mPlayer?.release()
        mPlayer = null
    }


    /**
     * initilize player ui
     * it is done on init as we want to show our audio controller when ui loads
     */
    private fun initPlayerUI() {
        if (mPlayer == null) {
            mPlayer = ExoPlayerFactory.newSimpleInstance(DefaultRenderersFactory(context), DefaultTrackSelector(), DefaultLoadControl())
            mPlayerView.player = mPlayer
        }
    }

    /**
     * initialize player with default/latest configuration and start playing video
     *
     * NOTE: not initilizing it when UI just loads, as we do not want to start buffering when just UI becomes visible
     * because in our case we are not 100% sure that user is gonna play this audio
     */
    private fun initializePlayer() {
        if (mAudioUrl.isEmpty()) {
            return
        }

        if (isPrepared) {
            /** if player is already initialise and continue playing**/
            mPlayer?.playWhenReady = true
            return
        }

        initPlayerUI()

        val uri = Uri.parse(mAudioUrl)

        val cacheFactory = CacheDataSourceFactory(AppUtils.getSimpleCache(context.applicationContext), DefaultDataSourceFactory(context, "audioStreamer"))

        /** instance of mediasource with input cache data source **/
        mMediaSource = ExtractorMediaSource.Factory(cacheFactory).createMediaSource(uri)

        mPlayer?.playWhenReady = true

        mPlayer?.prepare(mMediaSource, false, false)

        isPrepared = true
    }


}

