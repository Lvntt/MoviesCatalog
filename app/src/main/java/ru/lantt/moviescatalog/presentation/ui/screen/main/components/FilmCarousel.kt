package ru.lantt.moviescatalog.presentation.ui.screen.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.CarouselImageHeight
import ru.lantt.moviescatalog.presentation.ui.theme.LargeRoundedCornerRadius
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.TinyIconSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmCarousel(
    modifier: Modifier = Modifier
) {
    val filmCarouselItems = FilmCarouselItems.items
    val posterCount = filmCarouselItems.size
    val pageCount = Int.MAX_VALUE
    val pagerState = rememberPagerState(
        initialPage = pageCount / 2 + 1,
        initialPageOffsetFraction = 0f
    ) {
        pageCount
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(CarouselImageHeight)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
        ) { page ->
            val currentFilmPoster = filmCarouselItems[page % posterCount]

            Image(
                painter = painterResource(id = currentFilmPoster),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null
            )
        }
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                CarouselPagination(
                    pageCount = posterCount,
                    selectedPage = pagerState.currentPage % posterCount
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun CarouselPagination(
    pageCount: Int,
    selectedPage: Int
) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(LargeRoundedCornerRadius)
            )
            .padding(PaddingSmall)
    ) {
        Row {
            repeat(pageCount) {
                Image(
                    imageVector = if (it == selectedPage) ImageVector.vectorResource(id = R.drawable.dot_filled_icon)
                        else ImageVector.vectorResource(id = R.drawable.dot_outlined_icon),
                    contentDescription = null,
                    modifier = Modifier.size(TinyIconSize)
                )

                if (it != pageCount - 1) {
                    Spacer(modifier = Modifier.width(PaddingSmall))
                }
            }
        }
    }
}

private object FilmCarouselItems {
    val items = listOf(
        R.drawable.carousel_poster_1,
        R.drawable.carousel_poster_2,
        R.drawable.carousel_poster_3,
        R.drawable.carousel_poster_4
    )
}

@Preview
@Composable
fun FilmCarouselPreview() {
    FilmCarousel()
}