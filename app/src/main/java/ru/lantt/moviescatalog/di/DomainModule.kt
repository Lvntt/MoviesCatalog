package ru.lantt.moviescatalog.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.domain.usecase.LoginUserUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.domain.usecase.RegisterUserUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRegistrationUsernameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRegistrationPasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase

fun provideDomainModule(): Module = module {

    factory {
        ValidateNameUseCase()
    }

    factory {
        ValidateRegistrationUsernameUseCase()
    }

    factory {
        ValidateEmailUseCase()
    }

    factory {
        ValidateDateOfBirthUseCase()
    }

    factory {
        ValidateRegistrationPasswordUseCase()
    }

    factory {
        ValidateRepeatedPasswordUseCase()
    }

    factory {
        LoginUserUseCase(get())
    }

    factory {
        RegisterUserUseCase(get())
    }

    factory {
        LogoutUserUseCase(get())
    }

}