package com.zawmyat.rickandmortyuniverse.view.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zawmyat.rickandmortyuniverse.view.navigation.BottomNavigationBar
import com.zawmyat.rickandmortyuniverse.view.navigation.NavigationGraph
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel

@Composable
fun ScaffoldLayout() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}