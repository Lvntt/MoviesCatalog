package ru.lantt.moviescatalog.presentation.uistate.home

sealed interface HomeUiState {

    object Initial : HomeUiState

    object Loading : HomeUiState

    object Error : HomeUiState

}