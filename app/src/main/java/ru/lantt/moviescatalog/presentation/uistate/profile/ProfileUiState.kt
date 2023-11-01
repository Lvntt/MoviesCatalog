package ru.lantt.moviescatalog.presentation.uistate.profile

sealed interface ProfileUiState {

    object Initial : ProfileUiState

    object Loading : ProfileUiState

    object Error : ProfileUiState

    object Success : ProfileUiState

}