package ru.lantt.moviescatalog.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.lantt.moviescatalog.common.Constants
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.entity.Gender
import ru.lantt.moviescatalog.domain.entity.UserRegisterModel
import ru.lantt.moviescatalog.domain.usecase.RegisterUserUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateLoginUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidatePasswordUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateRepeatedPasswordUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes
import ru.lantt.moviescatalog.presentation.mapper.ErrorTypeToStringRes
import ru.lantt.moviescatalog.presentation.uistate.auth.register.RegistrationContent
import ru.lantt.moviescatalog.presentation.uistate.auth.register.RegistrationState
import ru.lantt.moviescatalog.presentation.uistate.auth.register.RegistrationUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class RegistrationViewModel(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateDateOfBirthUseCase: ValidateDateOfBirthUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    val registrationState: State<RegistrationState>
        get() = _registrationState
    private val _registrationState: MutableState<RegistrationState> = mutableStateOf(
        RegistrationState.RegistrationInfo
    )

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent
    private val _registrationContent = mutableStateOf(RegistrationContent())

    val registrationUiState: State<RegistrationUiState>
        get() = _registrationUiState
    private val _registrationUiState: MutableState<RegistrationUiState> = mutableStateOf(
        RegistrationUiState.Initial
    )

    private fun getErrorDescription(validationErrorType: ValidationErrorType?): Int? {
        if (validationErrorType == null) {
            return null
        }
        return ErrorTypeToStringRes.errors[validationErrorType]
    }

    private fun getFormattedDate(dateOfBirthMillis: Long?): String {
        if (dateOfBirthMillis == null) {
            return Constants.EMPTY_STRING
        }
        val simpleDateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        return simpleDateFormat.format(dateOfBirthMillis)
    }

    private fun formatDateOfBirthForRegistration(): String {
        val dateOfBirthMillis = _registrationContent.value.dateOfBirthMillis
        val dateFormat = SimpleDateFormat(REQUEST_DATE_PATTERN, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone(UTC)

        return dateFormat.format(Date(dateOfBirthMillis ?: DATE_ERROR_VALUE))
    }

    private val registrationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.BAD_REQUEST -> {
                    _registrationContent.value = _registrationContent.value.copy(isRegistrationError = true)
                    _registrationUiState.value = RegistrationUiState.Initial
                }
                else -> _registrationUiState.value = RegistrationUiState.Error
            }
            else -> _registrationUiState.value = RegistrationUiState.Error
        }
    }

    fun setName(name: String) {
        val validationResult = validateNameUseCase(name)
        val errorDescriptionId = getErrorDescription(validationResult)
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

    fun setUsername(username: String) {
        val validationResult = validateLoginUseCase(username)
        val errorDescriptionId = getErrorDescription(validationResult)
        _registrationContent.value = _registrationContent.value.copy(
            login = username,
            loginErrorId = errorDescriptionId
        )
    }

    fun setEmail(email: String) {
        val validationResult = validateEmailUseCase(email)
        val errorDescriptionId = getErrorDescription(validationResult)
        _registrationContent.value = _registrationContent.value.copy(
            email = email,
            emailErrorId = errorDescriptionId
        )
    }

    fun setDateOfBirth(dateOfBirthMillis: Long) {
        val validationResult = validateDateOfBirthUseCase(dateOfBirthMillis)
        val errorDescriptionId = getErrorDescription(validationResult)
        _registrationContent.value = _registrationContent.value.copy(
            dateOfBirth = getFormattedDate(dateOfBirthMillis),
            dateOfBirthMillis = dateOfBirthMillis,
            dateOfBirthErrorId = errorDescriptionId
        )
    }

    fun setPassword(password: String) {
        val validationResult = validatePasswordUseCase(password)
        val errorDescriptionId = getErrorDescription(validationResult)
        _registrationContent.value = _registrationContent.value.copy(
            password = password,
            passwordErrorId = errorDescriptionId
        )
    }

    fun setRepeatedPassword(repeatedPassword: String) {
        val password = _registrationContent.value.password
        val validationResult = validateRepeatedPasswordUseCase(password, repeatedPassword)
        val errorDescriptionId = getErrorDescription(validationResult)
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

    fun goToRegistrationInfoScreen() {
        _registrationState.value = RegistrationState.RegistrationInfo
    }

    fun registrationIsAllowed(): Boolean {
        val registrationContent = _registrationContent.value
        return canGoToPasswordScreen()
                && registrationContent.password.isNotEmpty()
                && registrationContent.repeatedPassword.isNotEmpty()
                && registrationContent.passwordErrorId == null
                && registrationContent.repeatedPasswordErrorId == null
                && _registrationUiState.value !is RegistrationUiState.Loading
    }

    fun onRegister() {
        _registrationUiState.value = RegistrationUiState.Loading
        _registrationContent.value = _registrationContent.value.copy(isRegistrationError = false)
        val birthDate = formatDateOfBirthForRegistration()
        viewModelScope.launch(Dispatchers.IO + registrationExceptionHandler) {
            registerUserUseCase(
                with (_registrationContent.value) {
                    UserRegisterModel(
                        userName = login,
                        name = name,
                        password = password,
                        email = email,
                        birthDate = birthDate,
                        gender = gender.ordinal
                    )
                }
            )
            _registrationUiState.value = RegistrationUiState.Success
        }
    }

    private companion object {
        const val DATE_ERROR_VALUE = 0L
        const val UTC = "UTC"
        const val DATE_PATTERN = "dd.MM.yyyy"
        const val REQUEST_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }

}