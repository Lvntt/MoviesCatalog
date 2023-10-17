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

    fun setLogin(login: String) {
        _loginContent.value = _loginContent.value.copy(login = login)
    }

    fun setPassword(password: String) {
        _loginContent.value = _loginContent.value.copy(password = password)
    }

    fun changePasswordVisibility() {
        val passwordIsVisible = _loginContent.value.passwordIsVisible
        _loginContent.value = _loginContent.value.copy(passwordIsVisible = !passwordIsVisible)
    }

    fun canLogIn(): Boolean {
        return _loginContent.value.login.isNotEmpty() && _loginContent.value.password.isNotEmpty()
    }

}