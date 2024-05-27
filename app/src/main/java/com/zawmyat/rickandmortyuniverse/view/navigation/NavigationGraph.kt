package com.zawmyat.rickandmortyuniverse.view.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.view.characters_page.CharacterDetails
import com.zawmyat.rickandmortyuniverse.view.characters_page.CharactersPage
import com.zawmyat.rickandmortyuniverse.view.episodes_page.EpisodesPage
import com.zawmyat.rickandmortyuniverse.view.favorite_page.FavoritePage
import com.zawmyat.rickandmortyuniverse.view.info_page.InfoPage
import com.zawmyat.rickandmortyuniverse.view.locations_page.LocationsPage
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    Surface {
        NavHost(
            navController = navController,
            startDestination = NavItems.Characters.routeName,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None},
            popExitTransition = { ExitTransition.None}
        ) {

            composable(NavItems.Characters.routeName) {
                CharactersPage(navController = navController)
            }

            composable(NavItems.Locations.routeName) {
                LocationsPage(navController = navController)
            }

            composable(NavItems.Favorite.routeName) {
                FavoritePage(navController = navController)
            }

            composable(NavItems.Info.routeName) {
                InfoPage(navController = navController)
            }

            composable(NavItems.Episodes.routeName) {
                EpisodesPage()
            }

        }
    }

}