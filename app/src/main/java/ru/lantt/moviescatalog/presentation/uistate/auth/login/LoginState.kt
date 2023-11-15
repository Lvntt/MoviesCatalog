package ru.lantt.moviescatalog.presentation.uistate.auth.login

sealed interface LoginState {

    object Editing : LoginState

    object Loading : LoginState

}