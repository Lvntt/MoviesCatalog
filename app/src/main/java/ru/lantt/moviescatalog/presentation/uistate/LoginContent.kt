package ru.lantt.moviescatalog.presentation.uistate

data class LoginContent(
    val login: String = "",
    val password: String = "",
    val passwordIsVisible: Boolean = false,
    val isError: Boolean = false
)
