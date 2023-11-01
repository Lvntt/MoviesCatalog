package ru.lantt.moviescatalog.data.network.mapper

import ru.lantt.moviescatalog.data.model.MovieElementModel
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.entity.ReviewShort
import ru.lantt.moviescatalog.domain.util.EntityMapper

class MovieNetworkMapper : EntityMapper<MovieElementModel, Movie> {

    override fun mapFromEntity(entity: MovieElementModel): Movie {
        return Movie(
            id = entity.id,
            name = entity.name,
            poster = entity.poster,
            year = entity.year,
            country = entity.country,
            genres = entity.genres,
            reviews = entity.reviews,
            rating = getMovieRating(entity.reviews)
        )
    }

    override fun mapToEntity(domainModel: Movie): MovieElementModel {
        return MovieElementModel(
            id = domainModel.id,
            name = domainModel.name,
            poster = domainModel.poster,
            year = domainModel.year,
            country = domainModel.country,
            genres = domainModel.genres,
            reviews = domainModel.reviews
        )
    }

    fun fromEntityList(initial: List<MovieElementModel>): List<Movie> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Movie>): List<MovieElementModel> {
        return initial.map { mapToEntity(it) }
    }

    private fun getMovieRating(reviews: List<ReviewShort>): Double? {
        if (reviews.isEmpty()) return null
        return reviews.sumOf { it.rating }.toDouble() / reviews.size
    }

}