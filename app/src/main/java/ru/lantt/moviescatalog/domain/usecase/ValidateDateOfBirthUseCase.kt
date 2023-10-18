package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult
import ru.lantt.moviescatalog.domain.util.Constants.MAX_AGE_IN_YEARS
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class ValidateDateOfBirthUseCase {

    operator fun invoke(dateOfBirthMillis: Long?): ValidationResult {

        if (dateOfBirthMillis == null) {
            return ValidationResult(
                isSuccessful = false,
                errorType = ErrorType.EMPTY_FIELD
            )
        }

        val dobLocalDate = Date(dateOfBirthMillis).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val minValidDate = LocalDate.now().minusYears(MAX_AGE_IN_YEARS.toLong())
        val maxValidDate = LocalDate.now()

        if (dobLocalDate.isBefore(minValidDate) || dobLocalDate.isAfter(maxValidDate)) {
            return ValidationResult(
                isSuccessful = false,
                errorType = ErrorType.INVALID_DATE_OF_BIRTH
            )
        }

        return ValidationResult(
            isSuccessful = true
        )
    }

}