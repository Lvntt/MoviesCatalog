package ru.lantt.moviescatalog.presentation.uistate.auth.login

sealed interface LoginUiState {

    object Initial : LoginUiState

    object Loading : LoginUiState

    object Error : LoginUiState

    object Success : LoginUiState

}