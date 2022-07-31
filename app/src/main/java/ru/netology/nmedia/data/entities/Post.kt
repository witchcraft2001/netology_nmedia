package ru.netology.nmedia.data.entities

data class Post(
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val text: String,
    val isFavorite: Boolean,
    val favoriteCount: Int,
    val commentsCount: Int,
    val repostsCount: Int,
    val viewsCount: Int
)
