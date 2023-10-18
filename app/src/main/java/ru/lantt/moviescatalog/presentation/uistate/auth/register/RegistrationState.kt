package ru.lantt.moviescatalog.presentation.uistate.auth.register

sealed interface RegistrationState {

    object RegistrationInfo : RegistrationState

    object RegistrationPassword : RegistrationState

}