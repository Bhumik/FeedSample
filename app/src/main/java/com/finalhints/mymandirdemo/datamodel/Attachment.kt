package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Attachment(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("url")
        var url: String? = null,
        @JsonProperty("post")
        var post: Int? = null,
        @JsonProperty("size")
        var size: Int? = null,
        @JsonProperty("type")
        var type: String? = null,
        @JsonProperty("user")
        var user: Int? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("temple")
        var temple: Int? = null,
        @JsonProperty("userName")
        var userName: String? = null,
        @JsonProperty("userImage")
        var userImage: String? = null,
        @JsonProperty("thumbnail_url")
        var thumbnailUrl: String? = null,
        @JsonProperty("watermark_url")
        var watermarkUrl: String? = null,
        @JsonProperty("medium_url")
        var mediumUrl: String? = null,
        @JsonProperty("downsampled_url")
        var downsampledUrl: String? = null,
        @JsonProperty("mobile_url")
        var mobileUrl: String? = null,
        @JsonProperty("metadata")
        var metadata: FeedMetadata = FeedMetadata()
) : Parcelable {

    val isVideo: Boolean
        get() {
            return type.equals("video", ignoreCase = true)
        }
    val isAudio: Boolean
        get() {
            return type.equals("audio", ignoreCase = true)
        }
}
