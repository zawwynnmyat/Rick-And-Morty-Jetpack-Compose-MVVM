package com.zawmyat.rickandmortyuniverse


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.FavCharacterObj
import com.zawmyat.rickandmortyuniverse.models.datastore.SortingStore
import com.zawmyat.rickandmortyuniverse.ui.theme.RickAndMortyUniverseTheme
import com.zawmyat.rickandmortyuniverse.view.scaffold.ScaffoldLayout
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            AppContext.applicationContext = LocalContext.current

            val sortDataStore = SortingStore(AppContext.applicationContext)
            val currentSortValue = sortDataStore.sortValueFlow.collectAsState(initial = "ID (Default)")

            AppContext.currentSortSetting = currentSortValue.value

            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFF09051D),
                    darkIcons = false
                )

                systemUiController.setNavigationBarColor(
                    color = Color(0xFF201E33),
                    darkIcons = true
                )

            }

            RickAndMortyUniverseTheme {

                val favCharViewModel = hiltViewModel<FavoriteCharacterViewModel>()
                FavCharacterObj.favoriteCharacterViewModel = favCharViewModel

                Surface(modifier = Modifier.fillMaxSize()) {
                    ScaffoldLayout()
                }

            }
        }
    }
}






