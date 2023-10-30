package ru.lantt.moviescatalog.data.model

import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.domain.entity.Review

data class MovieDetailsModel(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>,
    val reviews: List<Review>,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val ageLimit: Int,
    val rating: Double?
)
