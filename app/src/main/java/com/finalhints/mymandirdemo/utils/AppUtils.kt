package com.finalhints.mymandirdemo.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File
import java.util.*


object AppUtils {

    enum class EmptyState {
        LOADING,
        NO_NETWORK,
        GONE
    }

    var mSimpleCache: SimpleCache? = null
    //mSimpleCache = SimpleCache(getCacheFolder(context, "exo1"), NoOpCacheEvictor())


    fun getSimpleCache(context: Context): SimpleCache {
        if (mSimpleCache == null) {
            mSimpleCache = SimpleCache(getCacheFolder(context, "medias"), NoOpCacheEvictor())
        }
        return mSimpleCache!!
    }


    fun timeAgoInWords(from: Long?): String {
        from?.let {
            return timeAgoInWords(Date(it))
        }
        return "Unknown"
    }

    fun timeAgoInWords(from: Date?): String {
        if (from == null) {
            return "Unknown"
        }

        val now = Date()
        val difference = now.time - from.time
        val distanceInMin = difference / 60000

        if (distanceInMin in 0..1) {
            return "Less than 1 minute ago"
        } else if (distanceInMin in 1..45) {
            return "$distanceInMin minutes ago"
        } else if (distanceInMin in 45..89) {
            return "About 1 hour"
        } else if (distanceInMin in 90..1439) {
            return "About " + distanceInMin / 60 + " hours ago"
        } else if (distanceInMin in 1440..2529) {
            return "1 day"
        } else if (distanceInMin in 2530..43199) {
            return "${(distanceInMin / 1440)} days ago"
        } else if (distanceInMin in 43200..86399) {
            return "About 1 month ago"
        } else if (distanceInMin in 86400..525599) {
            return "About " + distanceInMin / 43200 + " months ago"
        } else {
            val distanceInYears = distanceInMin / 525600
            return "About $distanceInYears years ago"
        }
    }


    fun getCacheFolder(context: Context, name: String): File {
        return File(context.cacheDir, name)
    }


    /**
     * function to compact text for user with readmore
     * here it check is length upto 200 but then compact it with 150 only
     * because we do not want user to go in another screen just to read remaining 5-15 character in main text
     *
     * made inline ad lambda is used to avoid creating Function as it will be used multiple times in bindView of recyclerview
     */
    inline fun getCompatText(text: String, resultFunction: (isCompacted: Boolean, compactedText: SpannableString) -> Unit) {
        if (text.length > 200) {
            val spSt = SpannableString("${text.substring(0, 150)}\n\nRead More")
            spSt.setSpan(ForegroundColorSpan(Color.parseColor("#039be5")), spSt.length - 10, spSt.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spSt.setSpan(RelativeSizeSpan(1.2f), spSt.length - 10, spSt.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spSt.setSpan(StyleSpan(Typeface.BOLD), spSt.length - 10, spSt.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            resultFunction(true, spSt)
        } else {
            resultFunction(false, SpannableString(text))
        }
    }
}
