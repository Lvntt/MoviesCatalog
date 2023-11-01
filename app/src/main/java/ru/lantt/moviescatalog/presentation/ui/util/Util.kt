package ru.lantt.moviescatalog.presentation.ui.util

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import ru.lantt.moviescatalog.common.Constants.EMPTY_STRING
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Fire
import ru.lantt.moviescatalog.presentation.ui.theme.Green
import ru.lantt.moviescatalog.presentation.ui.theme.LightGreen
import ru.lantt.moviescatalog.presentation.ui.theme.Orange
import ru.lantt.moviescatalog.presentation.ui.theme.Red
import ru.lantt.moviescatalog.presentation.ui.theme.Yellow
import java.text.SimpleDateFormat
import java.util.Locale

const val INITIAL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"
const val UI_DATE_TIME_FORMAT = "dd.MM.yyyy"
const val UTIL_TAG = "Util.kt"

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.shimmerEffect(startOffsetX: Float): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF161616),
                Accent,
                Color(0xFF161616)
            ),
            start = Offset(startOffsetX * size.width.toFloat(), 0f),
            end = Offset(startOffsetX * size.width.toFloat() + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

fun getRatingColor(reviewRating: Double): Color {
    return when {
        reviewRating > 8 -> Green
        reviewRating > 6 -> LightGreen
        reviewRating > 4 -> Yellow
        reviewRating > 3 -> Orange
        reviewRating > 2 -> Fire
        else -> Red
    }
}

fun formatMoneyNumber(number: Int): String {
    val numberString = number.toString()
    val length = numberString.length
    val result = StringBuilder()

    for (i in 0 until length) {
        result.append(numberString[i])
        if ((length - i) % 3 == 1 && i < length - 1) {
            result.append(" ")
        }
    }

    return "$$result"
}

fun getFormattedDateTime(datetime: String): String {
    val sdf = SimpleDateFormat(INITIAL_DATE_TIME_FORMAT, Locale.getDefault())
    return try {
        val date = sdf.parse(datetime) ?: EMPTY_STRING
        SimpleDateFormat(UI_DATE_TIME_FORMAT, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        Log.e(UTIL_TAG, e.stackTraceToString())
        EMPTY_STRING
    }
}