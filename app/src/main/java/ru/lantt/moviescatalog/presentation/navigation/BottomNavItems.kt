package ru.lantt.moviescatalog.presentation.navigation

import ru.lantt.moviescatalog.R

object BottomNavItems {

    val items = listOf(
        BottomNavItem(
            labelId = R.string.main,
            iconId = R.drawable.home_icon,
            route = MoviesCatalogDestinations.MAIN
        ),
        BottomNavItem(
            labelId = R.string.favorite,
            iconId = R.drawable.heart_outlined_icon,
            route = MoviesCatalogDestinations.FAVORITES
        ),
        BottomNavItem(
            labelId = R.string.profile,
            iconId = R.drawable.profile_icon,
            route = MoviesCatalogDestinations.PROFILE
        )
    )

}