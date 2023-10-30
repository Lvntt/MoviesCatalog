package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.SecondaryButton
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.BorderDefault
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewDialog(
    onDismissRequest: () -> Unit,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO replace with VM field, change to val
    var reviewText by remember { mutableStateOf("") }
    // TODO replace with VM field, change to val
    var checked by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = Gray900
            ),
            shape = RoundedCornerShape(5.dp),
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.leave_a_review),
                    style = Title_2_B_20,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(DefaultPaddingBetweenElements))

                Column {
                    Box {
                        RatingBar(
                            currentRating = 0,
                            onRatingChanged = onRatingChanged,
                            modifier = Modifier.padding(2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(PaddingSmall))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 98.dp),
                        value = reviewText,
                        onValueChange = {
                            // TODO replace with VM method
                            reviewText = it
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.write_a_review),
                                style = Text_R_14,
                                color = Gray400
                            )
                        },
                        shape = RoundedCornerShape(3.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Gray900,
                            focusedContainerColor = Gray900,
                            unfocusedBorderColor = BorderDefault,
                            focusedBorderColor = BorderDefault,
                            unfocusedTextColor = Color.White,
                            focusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = {
                                // TODO replace with VM method
                                checked = it
                            },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = Color.White,
                                checkedColor = Accent
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = stringResource(id = R.string.anonymous_review),
                        style = Label_M_15,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                AccentButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = reviewText.isNotEmpty(),
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.save)
                )

                Spacer(modifier = Modifier.height(PaddingSmall))

                SecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    onClick = onDismissRequest,
                    text = stringResource(id = R.string.cancel)
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewDialogPreview() {
    ReviewDialog(onDismissRequest = {}, onRatingChanged = {})
}