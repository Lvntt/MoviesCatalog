package ru.lantt.moviescatalog.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.lantt.moviescatalog.common.Constants
import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.Gender
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateLoginUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidatePasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase
import ru.lantt.moviescatalog.presentation.mapper.ErrorTypeToStringRes
import ru.lantt.moviescatalog.presentation.uistate.RegistrationContent
import ru.lantt.moviescatalog.presentation.uistate.RegistrationState
import java.text.SimpleDateFormat
import java.util.Locale

class RegistrationViewModel(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateDateOfBirthUseCase: ValidateDateOfBirthUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase
) : ViewModel() {

    val registrationState: State<RegistrationState>
        get() = _registrationState
    private val _registrationState: MutableState<RegistrationState> = mutableStateOf(RegistrationState.RegistrationInfo)

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent
    private val _registrationContent = mutableStateOf(RegistrationContent())

    private fun getErrorDescription(errorType: ErrorType?): Int? {
        if (errorType == null) {
            return null
        }
        return ErrorTypeToStringRes.errors[errorType]
    }

    private fun getFormattedDate(dateOfBirthMillis: Long?): String {
        if (dateOfBirthMillis == null) {
            return Constants.EMPTY_STRING
        }
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return simpleDateFormat.format(dateOfBirthMillis)
    }

    fun setName(name: String) {
        val validationResult = validateNameUseCase(name)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            name = name,
            nameErrorId = errorDescriptionId
        )
    }

    fun setGender(genderIndex: Int) {
        val gender = if (genderIndex == 0) Gender.MALE else Gender.FEMALE
        _registrationContent.value = _registrationContent.value.copy(
            gender = gender
        )
    }

    fun setLogin(login: String) {
        val validationResult = validateLoginUseCase(login)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            login = login,
            loginErrorId = errorDescriptionId
        )
    }

    fun setEmail(email: String) {
        val validationResult = validateEmailUseCase(email)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            email = email,
            emailErrorId = errorDescriptionId
        )
    }

    fun setDateOfBirth(dateOfBirthMillis: Long) {
        val validationResult = validateDateOfBirthUseCase(dateOfBirthMillis)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            dateOfBirth = getFormattedDate(dateOfBirthMillis),
            dateOfBirthErrorId = errorDescriptionId
        )
    }

    fun setPassword(password: String) {
        val validationResult = validatePasswordUseCase(password)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            password = password,
            passwordErrorId = errorDescriptionId
        )
    }

    fun setRepeatedPassword(repeatedPassword: String) {
        val password = _registrationContent.value.password
        val validationResult = validateRepeatedPasswordUseCase(password, repeatedPassword)
        val errorDescriptionId = getErrorDescription(validationResult.errorType)
        _registrationContent.value = _registrationContent.value.copy(
            repeatedPassword = repeatedPassword,
            repeatedPasswordErrorId = errorDescriptionId
        )
    }

    fun changePasswordVisibility() {
        val passwordIsVisible = _registrationContent.value.passwordIsVisible
        _registrationContent.value = _registrationContent.value.copy(
            passwordIsVisible = !passwordIsVisible
        )
    }

    fun getChosenGenderIndex(): Int {
        val gender = _registrationContent.value.gender
        return if (gender == Gender.MALE) 0 else 1
    }

    fun goToPasswordScreen() {
        _registrationState.value = RegistrationState.RegistrationPassword
    }

    fun canGoToPasswordScreen(): Boolean {
        val registrationContent = _registrationContent.value
        return registrationContent.name.isNotEmpty()
                && registrationContent.login.isNotEmpty()
                && registrationContent.email.isNotEmpty()
                && registrationContent.dateOfBirth.isNotEmpty()
                && registrationContent.nameErrorId == null
                && registrationContent.loginErrorId == null
                && registrationContent.emailErrorId == null
                && registrationContent.dateOfBirthErrorId == null
    }

    fun registrationIsAllowed(): Boolean {
        val registrationContent = _registrationContent.value
        return canGoToPasswordScreen()
                && registrationContent.password.isNotEmpty()
                && registrationContent.repeatedPassword.isNotEmpty()
                && registrationContent.passwordErrorId == null
                && registrationContent.repeatedPasswordErrorId == null
    }

    fun onRegister() {
        // TODO
    }

}