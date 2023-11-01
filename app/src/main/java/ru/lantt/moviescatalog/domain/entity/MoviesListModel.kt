package ru.lantt.moviescatalog.domain.entity

import ru.lantt.moviescatalog.data.model.MovieElementModel

data class MoviesListModel(
    val movies: List<MovieElementModel>?
)
