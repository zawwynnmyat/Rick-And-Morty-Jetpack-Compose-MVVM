package com.zawmyat.rickandmortyuniverse.view.episodes_page

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun EpisodeAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "All Episodes",
                color = Color.White
            )
        },
        backgroundColor = colorResource(id = R.color.scaffold_color),
    )
}