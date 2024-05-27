package com.zawmyat.rickandmortyuniverse.view.navigation

import com.zawmyat.rickandmortyuniverse.R

sealed class NavItems(val title: String, val icon: Int, val routeName: String) {
    object Characters : NavItems(title = "Characters", icon = R.drawable.ic_character, routeName = "characters")
    object Locations: NavItems(title = "Locations", icon = R.drawable.ic_location, routeName = "locations")
    object Episodes: NavItems(title = "Episodes", icon = R.drawable.ic_movie, routeName = "episodes")
    object Favorite: NavItems(title = "Favorite", icon = R.drawable.ic_favorite, routeName = "favorite")
    object Info: NavItems(title = "Info", icon = R.drawable.ic_face, routeName = "info")
}