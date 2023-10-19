package ru.lantt.moviescatalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.presentation.viewmodel.LoginViewModel
import ru.lantt.moviescatalog.presentation.viewmodel.RegistrationViewModel

fun providePresentationModule(): Module = module {

    viewModel { LoginViewModel(get()) }

    viewModel { RegistrationViewModel(get(), get(), get(), get(), get(), get(), get()) }

}