package ru.lantt.moviescatalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.presentation.viewmodel.auth.LoginViewModel
import ru.lantt.moviescatalog.presentation.viewmodel.auth.RegistrationViewModel
import ru.lantt.moviescatalog.presentation.viewmodel.launch.LaunchViewModel
import ru.lantt.moviescatalog.presentation.viewmodel.main.MainScreenViewModel
import ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel

fun providePresentationModule(): Module = module {

    viewModel { LoginViewModel(get()) }

    viewModel { RegistrationViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { MainScreenViewModel(get(), get(), get(), get(), get()) }

    viewModel { LaunchViewModel(get()) }

    viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get()) }

}