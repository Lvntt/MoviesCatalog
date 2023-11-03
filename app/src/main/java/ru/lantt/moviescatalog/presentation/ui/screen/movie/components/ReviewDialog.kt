package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.common.AccentButton
import ru.lantt.moviescatalog.presentation.ui.screen.common.SecondaryButton
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.BorderDefault
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewDialog(
    viewModel: MovieViewModel,
    onDismissRequest: () -> Unit,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val reviewContent by remember { viewModel.reviewContent}
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
                .verticalScroll(rememberScrollState()),
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
                            currentRating = reviewContent.rating,
                            onRatingChanged = onRatingChanged,
                            modifier = Modifier.padding(2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(PaddingSmall))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 98.dp),
                        value = reviewContent.text,
                        onValueChange = viewModel::setReviewText,
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
                            checked = reviewContent.isAnonymous,
                            onCheckedChange = viewModel::setAnonymity,
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
                    enabled = reviewContent.text.isNotEmpty(),
                    onClick = {
                        if (reviewContent.id == null) {
                            viewModel.addReview()
                            Toast.makeText(
                                context,
                                context.getString(R.string.review_added),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.editReview()
                            Toast.makeText(
                                context,
                                context.getString(R.string.review_edited),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        onDismissRequest()
                    },
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