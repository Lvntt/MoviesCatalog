package ru.lantt.moviescatalog.presentation.ui.event

sealed interface LoginEvent {

    object LoginError : LoginEvent

    object LoggedIn : LoginEvent

}