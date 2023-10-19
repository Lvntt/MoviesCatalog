package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.util.Constants.MAX_AGE_IN_YEARS
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class ValidateDateOfBirthUseCase {

    operator fun invoke(dateOfBirthMillis: Long?): ValidationErrorType? {
        if (dateOfBirthMillis == null) return ValidationErrorType.EMPTY_FIELD

        val dobLocalDate = Date(dateOfBirthMillis).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val minValidDate = LocalDate.now().minusYears(MAX_AGE_IN_YEARS.toLong())
        val maxValidDate = LocalDate.now()

        if (dobLocalDate.isBefore(minValidDate) || dobLocalDate.isAfter(maxValidDate)) {
            return ValidationErrorType.INVALID_DATE_OF_BIRTH
        }
        return null
    }

}