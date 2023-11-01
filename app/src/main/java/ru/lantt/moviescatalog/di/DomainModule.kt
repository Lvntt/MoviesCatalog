package ru.lantt.moviescatalog.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.domain.usecase.AddFavoriteMovieUseCase
import ru.lantt.moviescatalog.domain.usecase.AddReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.CheckTokenExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.CheckUserExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.DeleteFavoriteMovieUseCase
import ru.lantt.moviescatalog.domain.usecase.DeleteReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.EditReviewUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetFavoriteMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.LoginUserUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.domain.usecase.RegisterUserUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateLoginUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidatePasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase

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

    factory { GetFavoriteMoviesUseCase(get()) }

    factory { AddFavoriteMovieUseCase(get()) }

    factory { DeleteFavoriteMovieUseCase(get()) }

    factory { AddReviewUseCase(get()) }

    factory { EditReviewUseCase(get()) }

    factory { DeleteReviewUseCase(get()) }

    factory { GetUserProfileUseCase(get()) }

    factory { EditUserProfileUseCase(get()) }

    factory { ValidateUrlUseCase() }

}