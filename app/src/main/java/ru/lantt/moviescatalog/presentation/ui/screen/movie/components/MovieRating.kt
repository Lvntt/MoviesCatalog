package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

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
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_15
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny
import ru.lantt.moviescatalog.presentation.ui.theme.RatingRoundedCornerRadius

@Composable
fun MovieRating(
    ratingColor: Color,
    rating: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = ratingColor,
                shape = RoundedCornerShape(RatingRoundedCornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String.format("%.1f", rating),
            style = Label_M_15,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = PaddingTiny)
        )
    }
}