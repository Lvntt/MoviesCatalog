package ru.lantt.moviescatalog.presentation.uistate

data class LoginContent(
    val login: String = "",
    val password: String = "",
    val errorId: Int? = null
)
