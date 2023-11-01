package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun RatingBar(
    currentRating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 10,
    onRatingChanged: (Int) -> Unit,
    starsColor: Color = Color(0xFFFFD54F)
) {
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) {
                    ImageVector.vectorResource(id = R.drawable.star_filled_icon)
                }
                else {
                    ImageVector.vectorResource(id = R.drawable.star_outlined_icon)
                },
                contentDescription = null,
                tint = if (i <= currentRating) starsColor
                else Color.Unspecified,
                modifier = Modifier
                    .noRippleClickable { onRatingChanged(i) }
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    var currentRating by remember { mutableIntStateOf(0) }

    RatingBar(
        currentRating = currentRating,
        onRatingChanged = {
            currentRating = it
        }
    )
}