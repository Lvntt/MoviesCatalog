package ru.lantt.moviescatalog.domain.entity

data class Review(
    val id: String,
    val rating: Int,
    val reviewText: String?,
    val isAnonymous: Boolean,
    val createDateTime: String,
    val author: UserShort?
)
