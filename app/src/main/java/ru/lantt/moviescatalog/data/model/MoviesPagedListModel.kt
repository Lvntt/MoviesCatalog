package ru.lantt.moviescatalog.data.model

data class MoviesPagedListModel(
    val pageInfo: PageInfoModel,
    val movies: List<MovieElementModel>
)
