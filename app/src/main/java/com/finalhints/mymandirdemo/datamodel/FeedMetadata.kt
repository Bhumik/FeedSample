package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class FeedMetadata(
        @JsonProperty("width")
        var width: Int = 400,
        @JsonProperty("height")
        var height: Int = 400
) : Parcelable