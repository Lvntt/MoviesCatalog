package ru.lantt.moviescatalog.data.model

import ru.lantt.moviescatalog.domain.entity.Genre
import ru.lantt.moviescatalog.domain.entity.ReviewShort

data class MovieElementModel(
    val id: String,
    val name: String,
    val poster: String,
    val year: Int,
    val country: String,
    val genres: List<Genre>,
    val reviews: List<ReviewShort>
)
