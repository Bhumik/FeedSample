package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class RecentReaction(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("staticUrl")
        var staticUrl: String? = null,
        @JsonProperty("thumbnailUrl")
        var thumbnailUrl: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("thumbnailUrlWithBackground")
        var thumbnailUrlWithBackground: String? = null,
        @JsonProperty("selectedImageUrl")
        var selectedImageUrl: String? = null,
        @JsonProperty("nameKey")
        var nameKey: String? = null
) : Parcelable