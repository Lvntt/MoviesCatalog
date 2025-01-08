package ru.lantt.moviescatalog.data.validator

import android.util.Patterns
import ru.lantt.moviescatalog.domain.validator.EmailValidator

class EmailValidatorImpl : EmailValidator {

    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}