package ru.lantt.moviescatalog.presentation.uistate.main

sealed interface MainUiState {

    object Initial : MainUiState

    object Loading : MainUiState

    object Error : MainUiState

    object Success : MainUiState

}