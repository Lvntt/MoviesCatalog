package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.SmallAvatarSize

@Composable
fun PlaceholderAvatar(
    modifier: Modifier = Modifier,
    size: Dp = SmallAvatarSize
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = Color(0xFFEBEDF0),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.profile_icon),
            modifier = Modifier
                .padding(PaddingSmall)
                .size(size),
            contentDescription = null,
            tint = Color.Black
        )
    }
}