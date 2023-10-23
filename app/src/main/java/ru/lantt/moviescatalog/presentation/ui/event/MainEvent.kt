package ru.lantt.moviescatalog.presentation.ui.event

sealed interface MainEvent {

    object AuthenticationRequired : MainEvent

}