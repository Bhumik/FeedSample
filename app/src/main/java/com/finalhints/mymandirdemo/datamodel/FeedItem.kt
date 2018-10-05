package com.finalhints.mymandirdemo.datamodel

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class FeedItem(
        @JsonProperty("xid")
        var xid: String? = null,
        @JsonProperty("temple")
        var temple: Long? = null,
        @JsonProperty("user")
        var user: Long? = null,
        @JsonProperty("text")
        var text: String? = null,
        @JsonProperty("postType")
        var postType: String? = null,
        @JsonProperty("type")
        var type: String? = null,
        @JsonProperty("commentCount")
        var commentCount: Long? = null,
        @JsonProperty("reactionCount")
        var reactionCount: Long? = null,
        @JsonProperty("likeCount")
        var likeCount: Long? = null,
        @JsonProperty("shareCount")
        var shareCount: Long? = null,
        @JsonProperty("isFeatured")
        var isFeatured: Boolean? = null,
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("precantCount")
        var precantCount: Long? = null,
        @JsonProperty("viewCount")
        var viewCount: Long? = null,
        @JsonProperty("isRepost")
        var isRepost: Boolean? = null,
        @JsonProperty("isHidden")
        var isHidden: Boolean? = null,
        @JsonProperty("isValidPost")
        var isValidPost: Boolean? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("isPNSent")
        var isPNSent: Boolean? = null,
        //      @JsonProperty("parentId")
//        var parentId: Any? = null,
        @JsonProperty("id")
        var id: Long? = null,
        @JsonProperty("createdAt")
        var createdAt: Long? = null,
        @JsonProperty("tags")
        var tags: ArrayList<Tag>? = null,
        //      @JsonProperty("likedBy")
//        var likedBy: ArrayList<Any>? = null,
        @JsonProperty("attachments")
        var attachments: ArrayList<Attachment>? = null,
        @JsonProperty("webPath")
        var webPath: String? = null,
        @JsonProperty("sender")
        var sender: Sender? = null,
        @JsonProperty("recentReactions")
        var recentReactions: List<RecentReaction>? = null,
        //      @JsonProperty("comments")
//        var comments: ArrayList<Any>? = null,
        @JsonProperty("fromCache")
        var fromCache: Boolean? = null,
        //      @JsonProperty("reactionsByViewer")
//        var reactionsByViewer: List<Any>? = null,
        @JsonProperty("liked")
        var liked: Boolean? = null,
        @JsonProperty("saved")
        var saved: Boolean? = null,
        @JsonProperty("userFollowsPoster")
        var userFollowsPoster: Boolean? = null
) : Parcelable