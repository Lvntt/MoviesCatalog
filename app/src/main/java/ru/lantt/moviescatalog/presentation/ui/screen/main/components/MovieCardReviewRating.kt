package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny
import ru.lantt.moviescatalog.presentation.ui.theme.ReviewRatingRoundedCornerRadius

@Composable
fun MovieCardReviewRating(
    reviewRatingColor: Color,
    reviewRating: String
) {
    Box {
        Row(
            modifier = Modifier
                .background(
                    color = reviewRatingColor,
                    shape = RoundedCornerShape(ReviewRatingRoundedCornerRadius)
                )
                .padding(PaddingTiny),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.star_icon),
                contentDescription = null,
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(PaddingTiny))

            Text(
                text = reviewRating,
                style = Label_M_15,
                color = Color.White
            )
        }
    }
}