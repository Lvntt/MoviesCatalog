package ru.lantt.moviescatalog.presentation.ui.screen.auth.registration.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.common.Constants
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import java.util.Calendar

// TODO change colors
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayPickerField(
    textFieldValue: String = Constants.EMPTY_STRING,
    onDatePick: (Long) -> Unit,
    isError: Boolean = false,
    errorId: Int? = null
) {
    var datePickerOpened by remember { mutableStateOf(false) }
    val currentDate = Calendar.getInstance().timeInMillis

    if (datePickerOpened) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {
                datePickerOpened = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerOpened = false
                        onDatePick(datePickerState.selectedDateMillis ?: currentDate)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.ok)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        datePickerOpened = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel)
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = Gray900
            )
        ) {
            DatePicker(
                state = datePickerState,
                dateValidator = {
                    it < currentDate
                }
            )
        }
    }

    BirthdayTextField(
        textFieldValue = textFieldValue,
        enabled = false,
        isError = isError,
        onPickDate = {
            datePickerOpened = true
        },
        errorId = errorId
    )
}