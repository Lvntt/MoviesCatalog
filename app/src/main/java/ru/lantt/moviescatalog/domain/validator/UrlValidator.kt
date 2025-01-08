package ru.lantt.moviescatalog.domain.validator

interface UrlValidator {

    fun isValid(url: String): Boolean
}