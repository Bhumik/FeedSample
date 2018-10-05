package com.finalhints.mymandirdemo.view.ui.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache


private const val EXTRA_CURRENT_INDEX = "EXTRA_CURRENT_INDEX"
private const val EXTRA_CURRENT_DATA = "EXTRA_CURRENT_DATA"


class ExoVideoActivity : AppCompatActivity() {

    companion object {
        fun startActivity(pContext: Context, attachment: Attachment, itemPosition: Int = 0) {
            val intent = Intent(pContext, ExoVideoActivity::class.java)
            intent.putExtra(EXTRA_CURRENT_DATA, attachment)
            intent.putExtra(EXTRA_CURRENT_INDEX, itemPosition)
            pContext.startActivity(intent)
        }
    }

    private val mPlayerView: PlayerView by lazy { findViewById<PlayerView>(R.id.exoPlayerView) }
    private val mIvRetry: ImageView by lazy { findViewById<ImageView>(R.id.ivRetry) }
    private var mPlayer: SimpleExoPlayer? = null

    private var mPlayWhenReady = true
    /**
     * cache to help reference
     */
    private var mSimpleCache: SimpleCache? = null

    /**
     * latest playback position from which to start playing video
     */
    private var playbackPosition: Long = 0

    private var mMediaSource: ExtractorMediaSource? = null

    private var mVideoUrl: String = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_10mb.mp4"

    private var mAttachment: Attachment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_video)
        setBundleProperties()

        supportActionBar?.hide()

        /* on horizontal layout, hide action and status bar to make full screen view while on verticle reset it */
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        if (savedInstanceState?.containsKey(EXTRA_CURRENT_INDEX) == true) {
            playbackPosition = savedInstanceState.getLong(EXTRA_CURRENT_INDEX, 0)
        }

        mIvRetry.setOnClickListener {
            mIvRetry.visibility = View.GONE
            prepare()
        }

        mIvRetry.visibility = View.GONE
        prepare()


        //we can add audio focus gain code to stop background music if playing
    }

    /**
     * set bundle properties
     */
    private fun setBundleProperties() {
        if (intent.hasExtra(EXTRA_CURRENT_DATA)) {
            mAttachment = intent.getParcelableExtra(EXTRA_CURRENT_DATA) as Attachment
        }
        mAttachment?.url?.let { mVideoUrl = it }
    }

    /**
     * save current playback position to bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_CURRENT_INDEX, mPlayer?.currentPosition ?: playbackPosition)
    }


    public override fun onStart() {
        super.onStart()
        initializePlayer()
    }


    public override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * release player to free resources and coder
     */
    private fun releasePlayer() {
        mPlayer?.let {
            playbackPosition = it.currentPosition
            mPlayWhenReady = it.playWhenReady
            it.release()
        }
        mSimpleCache?.release()
        mPlayer = null
    }

    /**
     * initialize player with default/latest configuration and start playing video
     */
    private fun initializePlayer() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())

        mPlayerView.player = mPlayer

        mPlayer?.apply {
            this.playWhenReady = mPlayWhenReady
            seekTo(playbackPosition)
        }


        mSimpleCache = SimpleCache(AppUtils.getCacheFolder(this, "exo1"), NoOpCacheEvictor())

        val uri = Uri.parse(mVideoUrl)

        val cacheFactory = CacheDataSourceFactory(mSimpleCache, DefaultDataSourceFactory(this, "videoStreamDemo1"))

        /* instance of mediasource with input cache data source */
        mMediaSource = ExtractorMediaSource.Factory(cacheFactory).createMediaSource(uri)

        mPlayer?.addListener(mPlayerListener)

        mPlayer?.prepare(mMediaSource, false, false)
    }

    /**
     * retry to play media
     */
    private fun prepare() {
        mPlayer?.prepare(mMediaSource, false, false)
        mPlayerView.showController()
    }

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
            if (playbackState == Player.STATE_READY && mPlayerView.layoutParams.height != FrameLayout.LayoutParams.WRAP_CONTENT) {
                mPlayerView.layoutParams.height = FrameLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }
}