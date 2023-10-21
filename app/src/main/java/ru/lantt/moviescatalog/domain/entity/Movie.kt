package ru.lantt.moviescatalog.domain.entity

data class Movie(
    val id: String,
    val name: String,
    val poster: String,
    val year: Int,
    val country: String,
    val genres: List<Genre>,
    val reviews: List<Review>,
    val reviewRating: Int
)
