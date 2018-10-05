package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
class Tag(
        @JsonProperty("id")
        var id: Int? = null,
        @JsonProperty("description")
        var description: String? = null,
        @JsonProperty("text")
        var text: String? = null,
        @JsonProperty("imageUrl")
        var imageUrl: String? = null
) : Parcelable
