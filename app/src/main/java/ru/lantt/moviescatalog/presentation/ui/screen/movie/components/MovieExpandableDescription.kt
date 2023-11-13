package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

const val DEFAULT_MINIMUM_TEXT_LINE = 4

@Composable
fun MovieExpandableDescription(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = Text_R_14,
    fontStyle: FontStyle? = null,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    textAlign: TextAlign? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .onGloballyPositioned {
                containerSize = it.size
            }
        ) {
            Text(
                modifier = textModifier
                    .fillMaxWidth()
                    .animateContentSize(),
                text = buildAnnotatedString {
                    if (clickable) {
                        if (isExpanded) {
                            append(text)
                        } else {
                            val adjustedText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                                .dropLastWhile { Character.isWhitespace(it) }
                            append(adjustedText)
                        }
                    } else {
                        append(text)
                    }
                },
                maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
                fontStyle = fontStyle,
                onTextLayout = { textLayoutResult ->
                    if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                        clickable = true
                        lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                    }
                },
                style = style,
                textAlign = textAlign,
                color = color
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .then(
                        if (isExpanded) {
                            Modifier
                        } else {
                            Modifier.background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Gray900,
                                    ),
                                    startY = 0f,
                                    endY = containerSize.height.toFloat()
                                )
                            )
                        }
                    )
            )
        }

        Row(
            modifier = Modifier
                .background(Gray900)
                .noRippleClickable {
                    isExpanded = !isExpanded
                },
        ) {
            Text(
                text = if (!isExpanded) {
                    stringResource(id = R.string.show_more)
                } else {
                    stringResource(id = R.string.show_less)
                },
                style = Label_M_14,
                color = Accent
            )

            Spacer(modifier = Modifier.width(PaddingSmall))

            Icon(
                imageVector = if (!isExpanded) {
                    Icons.Filled.KeyboardArrowDown
                } else {
                    Icons.Filled.KeyboardArrowUp
                },
                contentDescription = null,
                tint = Accent
            )
        }
    }
}