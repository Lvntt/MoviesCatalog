package ru.lantt.moviescatalog.domain.entity

data class ReviewModifyModel(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean
)
