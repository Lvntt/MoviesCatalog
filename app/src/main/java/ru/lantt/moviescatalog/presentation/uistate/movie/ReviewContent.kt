package ru.lantt.moviescatalog.presentation.uistate.movie

data class ReviewContent(
    val id: String? = null,
    val rating: Int = 0,
    val text: String = "",
    val isAnonymous: Boolean = false
)
