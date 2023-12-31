package ru.lantt.moviescatalog.presentation.viewmodel.movie

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.entity.Review
import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel
import ru.lantt.moviescatalog.domain.usecase.AddFavoriteMovieUseCase
import ru.lantt.moviescatalog.domain.usecase.AddReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.DeleteFavoriteMovieUseCase
import ru.lantt.moviescatalog.domain.usecase.DeleteReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.EditReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.GetFavoriteMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes
import ru.lantt.moviescatalog.presentation.ui.event.MovieEvent
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieDetailsContent
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUiState
import ru.lantt.moviescatalog.presentation.uistate.movie.ReviewContent
import ru.lantt.moviescatalog.presentation.uistate.movie.ReviewState

class MovieViewModel(
    private val movieId: String,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val addReviewUseCase: AddReviewUseCase,
    private val editReviewUseCase: EditReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    val movieUiState: State<MovieUiState>
        get() = _movieUiState
    private val _movieUiState: MutableState<MovieUiState> = mutableStateOf(MovieUiState.Initial)

    val reviewState: State<ReviewState>
        get() = _reviewState
    private val _reviewState: MutableState<ReviewState> = mutableStateOf(ReviewState.DialogClosed)

    private val movieEventChannel = Channel<MovieEvent>()
    val movieEventFlow = movieEventChannel.receiveAsFlow()

    private val _reviewContentRemote: MutableState<ReviewContent> = mutableStateOf(ReviewContent())

    val reviewContent: State<ReviewContent>
        get() = _reviewContent
    private val _reviewContent: MutableState<ReviewContent> = mutableStateOf(ReviewContent())

    private val movieExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    viewModelScope.launch {
                        logoutUserUseCase()
                        movieEventChannel.send(MovieEvent.AuthenticationRequired)
                    }
                    _movieUiState.value = MovieUiState.Initial
                }

                else -> _movieUiState.value = MovieUiState.Error
            }

            else -> _movieUiState.value = MovieUiState.Error
        }
    }

    init {
        _movieUiState.value = MovieUiState.Loading
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            loadMovieDetails()
        }
    }

    fun addFavoriteMovie() {
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            addFavoriteMovieUseCase(movieId)
            movieEventChannel.send(MovieEvent.FavoriteMovieAdded)
        }
        _movieUiState.value = MovieUiState.Content(
            (_movieUiState.value as MovieUiState.Content).movieDetailsContent.copy(
                isInFavorites = true
            )
        )
    }

    fun deleteFavoriteMovie() {
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            deleteFavoriteMovieUseCase(movieId)
            movieEventChannel.send(MovieEvent.FavoriteMovieDeleted)
        }
        _movieUiState.value = MovieUiState.Content(
            (_movieUiState.value as MovieUiState.Content).movieDetailsContent.copy(
                isInFavorites = false
            )
        )
    }

    fun addReview() {
        _reviewState.value = ReviewState.Loading
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            addReviewUseCase(
                movieId = movieId,
                reviewModifyModel = with(_reviewContent.value) {
                    ReviewModifyModel(
                        reviewText = text,
                        rating = rating,
                        isAnonymous = isAnonymous
                    )
                }
            )
            loadMovieDetails()
            movieEventChannel.send(MovieEvent.ReviewAdded)
            onCloseDialog()
        }
    }

    fun editReview() {
        _reviewState.value = ReviewState.Loading
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            editReviewUseCase(
                movieId = movieId,
                reviewId = _reviewContent.value.id ?: "",
                reviewModifyModel =
                with(_reviewContent.value) {
                    ReviewModifyModel(
                        reviewText = text,
                        rating = rating,
                        isAnonymous = isAnonymous
                    )
                }
            )
            loadMovieDetails()
            movieEventChannel.send(MovieEvent.ReviewEdited)
            onCloseDialog()
        }
    }

    fun deleteReview() {
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            deleteReviewUseCase(
                movieId = movieId,
                reviewId = _reviewContent.value.id ?: ""
            )
            _reviewContent.value = ReviewContent()
            loadMovieDetails()
            movieEventChannel.send(MovieEvent.ReviewDeleted)
        }
    }

    fun setRating(rating: Int) {
        _reviewContent.value = _reviewContent.value.copy(rating = rating)
    }

    fun setReviewText(text: String) {
        _reviewContent.value = _reviewContent.value.copy(text = text)
    }

    fun setAnonymity(isAnonymous: Boolean) {
        _reviewContent.value = _reviewContent.value.copy(isAnonymous = isAnonymous)
    }

    fun refresh() {
        _movieUiState.value = MovieUiState.Loading
        viewModelScope.launch(Dispatchers.IO + movieExceptionHandler) {
            loadMovieDetails()
        }
    }

    fun canSetAnonymity(): Boolean {
        return _reviewContentRemote.value.id == null || _reviewContentRemote.value.isAnonymous
    }

    fun onCloseDialog() {
        _reviewState.value = ReviewState.DialogClosed
        _reviewContent.value = _reviewContentRemote.value
    }

    fun onOpenDialog() {
        _reviewState.value = ReviewState.DialogOpened
    }

    fun canEditReview(): Boolean {
        return _reviewContentRemote.value != _reviewContent.value
                && _reviewContent.value.text.isNotBlank()
    }

    private suspend fun loadMovieDetails() {
        val movie = getMovieDetailsUseCase(movieId)
        val userId = getUserIdFromLocalStorageUseCase()
        val favorites = getFavoriteMoviesUseCase()
        val myReview = getMyReview(movie.reviews, userId)
        val usersReviews = getUsersReviews(movie.reviews, userId)
        val movieRating = getMovieRating(movie.reviews)
        val isInFavorites = isInFavorites(favorites, movieId)

        _movieUiState.value = MovieUiState.Content(
            with(movie) {
                MovieDetailsContent(
                    id = id,
                    name = name,
                    poster = poster,
                    year = year,
                    country = country,
                    genres = genres,
                    myReview = myReview,
                    usersReviews = usersReviews,
                    time = time,
                    tagline = tagline,
                    description = description,
                    director = director,
                    budget = budget,
                    fees = fees,
                    ageLimit = ageLimit,
                    rating = movieRating,
                    isInFavorites = isInFavorites
                )
            }
        )

        if (myReview != null) {
            _reviewContentRemote.value = _reviewContentRemote.value.copy(
                id = myReview.id,
                rating = myReview.rating,
                text = myReview.reviewText ?: "",
                isAnonymous = myReview.isAnonymous
            )
            _reviewContent.value = _reviewContentRemote.value
        }
    }

    private fun getMyReview(reviews: List<Review>, userId: String?): Review? {
        if (userId == null) return null
        return reviews.find { it.author?.userId == userId }
    }


    /**
     * Get users' reviews, excluding user's own review
     */
    private fun getUsersReviews(reviews: List<Review>, userId: String?): List<Review> {
        if (userId == null) return reviews
        return reviews.filter { it.author?.userId != userId }
    }

    private fun getMovieRating(reviews: List<Review>): Double? {
        if (reviews.isEmpty()) return null
        return reviews.sumOf { it.rating }.toDouble() / reviews.size
    }

    private fun isInFavorites(favorites: List<Movie>, movieId: String): Boolean {
        return favorites.any { it.id == movieId }
    }

}