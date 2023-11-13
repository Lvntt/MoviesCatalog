package ru.lantt.moviescatalog.presentation.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_13
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.RatingRoundedCornerRadius

@Composable
fun MovieCardRating(
    ratingColor: Color,
    rating: Double
) {
    Box(modifier = Modifier.padding(start = 2.dp, top = 2.dp)) {
        Box(
            modifier = Modifier
                .background(
                    color = ratingColor,
                    shape = RoundedCornerShape(RatingRoundedCornerRadius)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%.1f", rating),
                style = Label_B_13,
                color = Gray900,
                modifier = Modifier.padding(horizontal = PaddingSmall, vertical = 2.dp)
            )
        }
    }
}