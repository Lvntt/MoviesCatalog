package ru.lantt.moviescatalog.presentation.uistate.movie

data class MovieUpdateModel(
    val userRating: Int?,
    val movieRating: Double?,
    val isInFavorites: Boolean
)
