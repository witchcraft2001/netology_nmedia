package ru.netology.nmedia.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Post(
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val text: String,
    val video: String?,
    val isLiked: Boolean,
    val isShared: Boolean,
    val likesCount: Int,
    val commentsCount: Int,
    val repostsCount: Int,
    val viewsCount: Int
) : Parcelable {
    companion object {
        fun getEmpty() = Post(
            0,
            "Anonymous",
            "",
            "",
            "",
            null,
            false,
            false,
            0,
            0,
            0,
            0
        )
    }
}
