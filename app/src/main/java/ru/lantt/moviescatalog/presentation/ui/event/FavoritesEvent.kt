package ru.lantt.moviescatalog.presentation.ui.event

sealed interface FavoritesEvent {

    object AuthenticationRequired : FavoritesEvent

}