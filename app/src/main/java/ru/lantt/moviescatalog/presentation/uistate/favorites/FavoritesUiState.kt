package ru.lantt.moviescatalog.presentation.uistate.favorites

import ru.lantt.moviescatalog.domain.entity.Movie

sealed interface FavoritesUiState {

    object Initial : FavoritesUiState

    object Loading : FavoritesUiState

    object Error : FavoritesUiState

    data class Content(val favorites: List<Movie>) : FavoritesUiState

}