package ru.lantt.moviescatalog.presentation.uistate.auth.register

import ru.lantt.moviescatalog.domain.entity.Gender

data class RegistrationContent(
    val name: String = "",
    val nameErrorId: Int? = null,
    val gender: Gender = Gender.MALE,
    val login: String = "",
    val loginErrorId: Int? = null,
    val email: String = "",
    val emailErrorId: Int? = null,
    val dateOfBirth: String = "",
    val dateOfBirthErrorId: Int? = null,
    val password: String = "",
    val passwordErrorId: Int? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordErrorId: Int? = null,
    val passwordIsVisible: Boolean = false
)
