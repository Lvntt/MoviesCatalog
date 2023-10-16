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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.common.Constants
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.DarkGray
import ru.lantt.moviescatalog.presentation.ui.theme.Gray
import java.util.Calendar

// TODO change colors
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayPickerField(
    modifier: Modifier = Modifier,
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
                titleContentColor = Color.White,
                headlineContentColor = Color.White,
                weekdayContentColor = Color.White,
                subheadContentColor = Color.White,
                yearContentColor = Color.White,
                currentYearContentColor = Color.White,
                selectedYearContentColor = Color.White,
                selectedYearContainerColor = Accent,
                dayContentColor = Color.White,
                disabledDayContentColor = DarkGray,
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = Accent,
                disabledSelectedDayContainerColor = Gray,
                disabledSelectedDayContentColor = DarkGray,
                todayContentColor = Accent,
                todayDateBorderColor = Accent,
                dayInSelectionRangeContainerColor = DarkGray,
                dayInSelectionRangeContentColor = Color.White
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
        enabled = true,
        isError = isError,
        onCalendarClick = {
            // TODO invoke VM method
            datePickerOpened = true
        },
        errorId = null
    )
}