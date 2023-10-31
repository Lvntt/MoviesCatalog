package ru.lantt.moviescatalog.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.domain.usecase.CheckTokenExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.CheckUserExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.EditUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.LoginUserUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.domain.usecase.RegisterUserUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateLoginUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidatePasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateUrlUseCase

fun provideDomainModule(): Module = module {

    factory { ValidateNameUseCase() }

    factory { ValidateLoginUseCase() }

    factory { ValidateEmailUseCase() }

    factory { ValidateDateOfBirthUseCase() }

    factory { ValidatePasswordUseCase() }

    factory { ValidateRepeatedPasswordUseCase() }

    factory { LoginUserUseCase(get()) }

    factory { RegisterUserUseCase(get()) }

    factory { LogoutUserUseCase(get()) }

    factory { GetMoviesUseCase(get()) }

    factory { GetAndSaveUserProfileUseCase(get()) }

    factory { GetMovieDetailsUseCase(get()) }

    factory { GetUserIdFromLocalStorageUseCase(get()) }

    factory { CheckUserExistenceUseCase(get()) }

    factory { CheckTokenExistenceUseCase(get()) }

    factory { GetUserProfileUseCase(get()) }

    factory { EditUserProfileUseCase(get()) }

    factory { ValidateUrlUseCase() }

}