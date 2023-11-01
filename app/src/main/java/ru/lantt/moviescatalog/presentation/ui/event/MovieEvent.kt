package ru.lantt.moviescatalog.presentation.ui.event

sealed interface MovieEvent {

    object AuthenticationRequired : MovieEvent

}