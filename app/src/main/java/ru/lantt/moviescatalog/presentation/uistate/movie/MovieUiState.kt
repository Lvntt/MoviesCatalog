package ru.lantt.moviescatalog.presentation.uistate.movie

sealed interface MovieUiState {

    object Initial : MovieUiState

    object Loading : MovieUiState

    object Error : MovieUiState

    data class Content(val movieDetailsContent: MovieDetailsContent) : MovieUiState

}