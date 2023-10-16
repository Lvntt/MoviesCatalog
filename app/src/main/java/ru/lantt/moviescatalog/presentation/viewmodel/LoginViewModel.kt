package ru.lantt.moviescatalog.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.lantt.moviescatalog.presentation.uistate.LoginContent

class LoginViewModel : ViewModel() {

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent: MutableState<LoginContent> = mutableStateOf(LoginContent())



}