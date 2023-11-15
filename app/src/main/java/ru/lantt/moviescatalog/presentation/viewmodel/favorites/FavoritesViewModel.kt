package ru.lantt.moviescatalog.presentation.viewmodel.favorites

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
import ru.lantt.moviescatalog.domain.usecase.CheckUserExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetFavoriteMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes
import ru.lantt.moviescatalog.presentation.ui.event.FavoritesEvent
import ru.lantt.moviescatalog.presentation.uistate.favorites.FavoritesUiState

class FavoritesViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val checkUserExistenceUseCase: CheckUserExistenceUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getAndSaveUserProfileUseCase: GetAndSaveUserProfileUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val favoritesUiState: State<FavoritesUiState>
        get() = _favoritesUiState
    private val _favoritesUiState: MutableState<FavoritesUiState> = mutableStateOf(
        FavoritesUiState.Initial
    )

    private val favoritesEventChannel = Channel<FavoritesEvent>()
    val favoritesEventFlow = favoritesEventChannel.receiveAsFlow()

    private val favoritesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    viewModelScope.launch {
                        logoutUserUseCase()
                        favoritesEventChannel.send(FavoritesEvent.AuthenticationRequired)
                    }
                    _favoritesUiState.value = FavoritesUiState.Initial
                }

                else -> _favoritesUiState.value = FavoritesUiState.Error
            }

            else -> _favoritesUiState.value = FavoritesUiState.Error
        }
    }

    init {
        loadFavoriteMovies()
    }

    fun retry() {
        loadFavoriteMovies()
    }

    private fun loadFavoriteMovies() {
        _favoritesUiState.value = FavoritesUiState.Loading
        viewModelScope.launch(Dispatchers.IO + favoritesExceptionHandler) {
            val userId = if (checkUserExistenceUseCase()) {
                getUserIdFromLocalStorageUseCase()
            } else {
                getAndSaveUserProfileUseCase().id
            }

            val favoriteMovies = getFavoriteMoviesUseCase()
            favoriteMovies.forEach {  favoriteMovie ->
                val movieDetails = getMovieDetailsUseCase(favoriteMovie.id)
                val reviewRating = movieDetails.reviews.find { it.author?.userId == userId }?.rating
                favoriteMovie.reviewRating = reviewRating
            }
            _favoritesUiState.value = FavoritesUiState.Content(favoriteMovies)
        }
    }

}