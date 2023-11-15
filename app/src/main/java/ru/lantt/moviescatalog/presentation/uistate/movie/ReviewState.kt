package ru.lantt.moviescatalog.presentation.uistate.movie

sealed interface ReviewState {

    object Loading : ReviewState

    object DialogOpened : ReviewState

    object DialogClosed : ReviewState

}