package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.MovieCardReviewRating
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Gray750
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14
import ru.lantt.moviescatalog.presentation.ui.theme.Label_R_13
import ru.lantt.moviescatalog.presentation.ui.theme.LightAccent
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.RoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun MovieMyReviewItemInfo(
    nickname: String?,
    rating: Int?,
    isAnonymous: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MovieRegularPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            if (isAnonymous) {
                Text(
                    text = stringResource(id = R.string.anonymous_user),
                    style = Label_M_14,
                    color = Color.White
                )
            } else {
                if (nickname != null) {
                    Text(
                        text = nickname,
                        style = Label_M_14,
                        color = Color.White
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.my_review),
                style = Label_R_13,
                color = Gray400
            )
        }

        MovieCardReviewRating(reviewRating = rating)

        Box(
            modifier = Modifier
                .noRippleClickable {
                    isMenuExpanded = !isMenuExpanded
                }
                .background(
                    color = Gray750,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ellipsis_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(3.dp)
            )
            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(
                    extraSmall = RoundedCornerShape(
                        RoundedCornerRadius
                    )
                )
            ) {
                DropdownMenu(
                    modifier = Modifier.background(color = Gray750),
                    expanded = isMenuExpanded,
                    onDismissRequest = {
                        isMenuExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = R.string.edit),
                                style = Label_M_14,
                                color = Color.White
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.edit_icon),
                                tint = Color.White,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            isMenuExpanded = false
                            onEditClick()
                        }
                    )
                    Divider(color = Color(0xFF55595D))
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = R.string.delete),
                                style = Label_M_14,
                                color = LightAccent
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.delete_icon),
                                tint = LightAccent,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            isMenuExpanded = false
                            onDeleteClick()
                        }
                    )
                }
            }
        }
    }
}