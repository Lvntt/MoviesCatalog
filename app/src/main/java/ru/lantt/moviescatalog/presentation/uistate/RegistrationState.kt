package ru.lantt.moviescatalog.presentation.uistate

sealed interface RegistrationState {

    object RegistrationInfo : RegistrationState

    object RegistrationPassword : RegistrationState

}