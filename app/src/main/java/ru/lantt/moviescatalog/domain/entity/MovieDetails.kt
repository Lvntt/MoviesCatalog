package ru.lantt.moviescatalog.domain.entity

data class MovieDetails(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>,
    val myReview: Review?,
    val usersReviews: List<Review>,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val ageLimit: Int,
    val rating: Double?,
    val isInFavorites: Boolean
)
