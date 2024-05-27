package com.zawmyat.rickandmortyuniverse.view.characters_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.view.characters_page.ui.theme.RickAndMortyUniverseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var name = ""
        var id = 0

        val bundle = intent.extras

        bundle?.let {
            name = it.getString("name") ?: ""
            id = it.getInt("id") ?: 0
        }


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

            RickAndMortyUniverseTheme {


                CharacterDetails(
                    name = name,
                    onFinishClicked = {
                        finish()
                    },
                    id = id,
                )

            }
        }
    }
}
