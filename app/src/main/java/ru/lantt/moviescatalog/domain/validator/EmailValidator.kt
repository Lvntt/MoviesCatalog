package ru.lantt.moviescatalog.domain.validator

interface EmailValidator {

    fun isValid(email: String): Boolean
}