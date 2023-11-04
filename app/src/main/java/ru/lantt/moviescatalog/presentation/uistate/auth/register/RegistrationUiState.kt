package ru.lantt.moviescatalog.presentation.uistate.auth.register

sealed interface RegistrationUiState {

    object Initial : RegistrationUiState

    object Loading : RegistrationUiState

    object Error : RegistrationUiState

    object Success : RegistrationUiState

}