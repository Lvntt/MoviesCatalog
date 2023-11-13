package ru.lantt.moviescatalog.data.network.mapper

import ru.lantt.moviescatalog.data.model.MovieElementModel
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.entity.ReviewShort
import ru.lantt.moviescatalog.domain.util.EntityMapper

class MovieNetworkMapper : EntityMapper<MovieElementModel, Movie> {

    override fun mapFromEntity(entity: MovieElementModel): Movie {
        with (entity) {
            return Movie(
                id = id,
                name = name,
                poster = poster,
                year = year,
                country = country,
                genres = genres,
                reviews = reviews,
                rating = getMovieRating(reviews)
            )
        }
    }

    override fun mapToEntity(domainModel: Movie): MovieElementModel {
        with (domainModel) {
            return MovieElementModel(
                id = id,
                name = name,
                poster = poster,
                year = year,
                country = country,
                genres = genres,
                reviews = reviews
            )
        }
    }

    fun fromEntityList(initial: List<MovieElementModel>): List<Movie> {
        return initial.map { mapFromEntity(it) }
    }

    private fun getMovieRating(reviews: List<ReviewShort>): Double? {
        if (reviews.isEmpty()) return null
        return reviews.sumOf { it.rating }.toDouble() / reviews.size
    }

}