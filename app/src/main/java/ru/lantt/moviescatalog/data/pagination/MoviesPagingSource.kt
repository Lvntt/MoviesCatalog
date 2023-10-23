package ru.lantt.moviescatalog.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import java.io.IOException

class MoviesPagingSource(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 0
            val response = getMoviesUseCase(page + 1)
            // TODO add use case to check user existence, get it from the net if it does not exist
            // TODO remove fetching user id from the net from VM
            val userId = getUserIdFromLocalStorageUseCase()
            var reviewRating: Int?

            response.forEach { movie ->
                val movieDetails = getMovieDetailsUseCase(movie.id)
                reviewRating = movieDetails.reviews.find { it.author?.userId == userId }?.rating
                movie.reviewRating = reviewRating
            }

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}