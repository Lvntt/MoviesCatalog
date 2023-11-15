package ru.lantt.moviescatalog.presentation.ui.event

sealed interface MovieEvent {

    object AuthenticationRequired : MovieEvent

    object ReviewAdded : MovieEvent

    object ReviewEdited : MovieEvent

    object ReviewDeleted : MovieEvent

    object FavoriteMovieAdded : MovieEvent

    object FavoriteMovieDeleted : MovieEvent

}