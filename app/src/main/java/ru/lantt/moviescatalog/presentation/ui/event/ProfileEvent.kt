package ru.lantt.moviescatalog.presentation.ui.event

sealed interface ProfileEvent {

    object AuthenticationRequired : ProfileEvent

}