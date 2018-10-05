package com.finalhints.mymandirdemo.widgets

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import com.facebook.drawee.view.SimpleDraweeView
import com.finalhints.mymandirdemo.datamodel.Attachment
import com.finalhints.mymandirdemo.utils.AppUtils
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import kotlinx.android.synthetic.main.layout_widget_exovideoview.view.*


class ExoVideoView : FrameLayout {

    /*view of layout*/
    private lateinit var mPlayerView: PlayerView
    private lateinit var mIvRetry: ImageView
    private lateinit var mIvPlay: ImageView
    private lateinit var mExoThumbPreview: SimpleDraweeView

    private var mPlayer: SimpleExoPlayer? = null

    /**
     * media source
     */
    private var mMediaSource: ExtractorMediaSource? = null

    /**
     * variable pointing to video url to play
     */
    private var mVideoUrl: String = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_10mb.mp4"

    /**
     * image thumbnail preview url
     */
    private var mPreviewUrl: String = ""

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
        val view = LayoutInflater.from(context).inflate(R.layout.layout_widget_exovideoview, this, true)
        mPlayerView = view.findViewById<View>(R.id.exoPlayerView) as PlayerView
        mExoThumbPreview = view.findViewById<SimpleDraweeView>(R.id.exoThumbPreview) as SimpleDraweeView
        mIvRetry = view.findViewById<View>(R.id.ivRetry) as ImageView
        mIvPlay = view.findViewById<View>(R.id.ivPlay) as ImageView

        mIvRetry.setOnClickListener {
            mIvRetry.visibility = View.GONE
            prepare()
        }
        mIvPlay.setOnClickListener {
            mIvPlay.visibility = View.GONE
            initializeAndPlayPlayer()
        }

        mPlayerView.hideController()
    }

    /**
     * set video url to play in playerview
     */
    public fun setVideoUrl(videoUrl: String) {
        mVideoUrl = videoUrl
    }

    /**
     * set video url to play in playerview
     */
    public fun setPreviewUrl(previewUrl: String) {
        mPreviewUrl = previewUrl
    }

    /**
     * set video url to play in playerview
     */
    public fun setFeedData(attachment: Attachment) {
        mVideoUrl = attachment.url ?: ""
        mPreviewUrl = attachment.thumbnailUrl ?: ""

        val aspectRatio = (attachment.metadata.width.toFloat() / attachment.metadata.height)
        exoThumbPreview.aspectRatio = aspectRatio

        exoThumbPreview.setImageURI(Uri.parse(mPreviewUrl), context)
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
        //stop listening for stop video playing requests
        //    context.unregisterReceiver(mBroadcastReceiver)

        mPlayer?.release()
        mPlayerView.hideController()
        isPrepared = false
        mPlayer = null
        mIvPlay.isVisible = true
        mPlayerView.isVisible = false
    }

    /**
     * initialize player with default/latest configuration and start playing video
     */
    private fun initializeAndPlayPlayer() {
        if (isPrepared) {
            return
        }
        mPlayerView.isVisible = true
        mPlayer = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(context),
                DefaultTrackSelector(), DefaultLoadControl())

        mPlayerView.player = mPlayer

        val uri = Uri.parse(mVideoUrl)

        val cacheFactory = CacheDataSourceFactory(AppUtils.getSimpleCache(context.applicationContext), DefaultDataSourceFactory(context, "videoStreamDemo1"))

        mPlayer?.playWhenReady = true
        /* instance of mediasource with input cache data source */
        mMediaSource = ExtractorMediaSource.Factory(cacheFactory).createMediaSource(uri)
        //mMediaSource = ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, "videoStreamDemo1")).createMediaSource(uri)

        mPlayer?.addListener(mPlayerListener)

        mPlayer?.prepare(mMediaSource, false, false)

        isPrepared = true
    }

    /**
     * retry to play media
     */
    private fun prepare() {
        mPlayer?.prepare(mMediaSource, false, false)
        mPlayerView.showController()
    }


    /**
     * object of player listener to get play error and state information
     */
    private val mPlayerListener = object : Player.EventListener {
        override fun onPlayerError(error: ExoPlaybackException?) {
            //in case of any error show retry button and retry media play on its click
            error?.printStackTrace()
            mIvRetry.visibility = View.VISIBLE
            mPlayerView.hideController()
        }

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}
        override fun onSeekProcessed() {}
        override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {}
        override fun onLoadingChanged(isLoading: Boolean) {}
        override fun onPositionDiscontinuity(reason: Int) {}
        override fun onRepeatModeChanged(repeatMode: Int) {}
        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}
        override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            //resize view as per video dimension when we start receiveing video frames
            if (playbackState == Player.STATE_READY && mPlayerView.layoutParams.height != FrameLayout.LayoutParams.WRAP_CONTENT) {
                mPlayerView.layoutParams.height = FrameLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }


    /**
     * broadcast receiver to check event to release player
     */
    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "STOP_IT") {
                releasePlayer()
                mIvPlay.isVisible = true
                mIvRetry.isVisible = false
            }
        }
    }

}

