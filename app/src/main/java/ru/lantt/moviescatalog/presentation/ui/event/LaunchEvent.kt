package ru.lantt.moviescatalog.presentation.ui.event

sealed interface LaunchEvent {

    object AuthenticationRequired : LaunchEvent

    object RedirectionToHomeRequired : LaunchEvent

}