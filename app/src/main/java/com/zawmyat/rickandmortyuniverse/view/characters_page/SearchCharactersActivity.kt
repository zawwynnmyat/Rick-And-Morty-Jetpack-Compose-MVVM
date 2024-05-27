package com.zawmyat.rickandmortyuniverse.view.characters_page

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zawmyat.rickandmortyuniverse.view.characters_page.character_search.SearchScaffold
import com.zawmyat.rickandmortyuniverse.view.characters_page.ui.theme.RickAndMortyUniverseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCharactersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFF09051D),
                    darkIcons = false
                )

                systemUiController.setNavigationBarColor(
                    color = Color(0xFF09051D),
                    darkIcons = true
                )
            }

            val context : Context = LocalContext.current

            RickAndMortyUniverseTheme {
                SearchScaffold(
                    onFinishClicked = {
                        finish()
                    },
                    context = context
                )
            }
        }
    }
}



