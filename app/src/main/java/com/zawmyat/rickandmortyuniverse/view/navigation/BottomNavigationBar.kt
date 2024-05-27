package com.zawmyat.rickandmortyuniverse.view.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf<NavItems>(
        NavItems.Characters,
        NavItems.Locations,
        NavItems.Episodes,
        NavItems.Favorite,
        NavItems.Info
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.nav_color),
    ) {

        items.forEach() {
                item ->
            BottomNavigationItem(
                selected = item.routeName == currentRoute,
                onClick = {

                    navController.navigate(item.routeName) {
                        navController.graph.startDestinationRoute?.let {
                                route_name ->
                            popUpTo(route_name) {
                                saveState = true
                                inclusive = true
                            }
                              launchSingleTop = true
                            restoreState = true
                        }
                    }

                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = {
                    Text(
                        text = item.title,
                        softWrap = false
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
            )
        }
    }

}