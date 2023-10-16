package ru.lantt.moviescatalog.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateLoginUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidatePasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase

fun provideDomainModule(): Module = module {

    factory {
        ValidateNameUseCase()
    }

    factory {
        ValidateLoginUseCase()
    }

    factory {
        ValidateEmailUseCase()
    }

    factory {
        ValidateDateOfBirthUseCase()
    }

    factory {
        ValidatePasswordUseCase()
    }

    factory {
        ValidateRepeatedPasswordUseCase()
    }

}