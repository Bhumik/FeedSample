package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Sender(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("imageUrl")
        var imageUrl: String? = null,
        @JsonProperty("language")
        var language: String? = null,
        @JsonProperty("status")
        var status: String? = null,
        @JsonProperty("friendlyId")
        var friendlyId: String? = null,
        @JsonProperty("thumbnailUrl")
        var thumbnailUrl: String? = null,
        @JsonProperty("microThumbnailUrl")
        var microThumbnailUrl: String? = null,
        @JsonProperty("isIdentityVerified")
        var isIdentityVerified: Boolean? = null,
        @JsonProperty("webPath")
        var webPath: String? = null

) : Parcelable {


}
