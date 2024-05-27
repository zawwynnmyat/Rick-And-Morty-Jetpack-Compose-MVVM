package com.zawmyat.rickandmortyuniverse.view.characters_page

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun CharacterDetailsAppBar(name: String, onFinishClicked : () -> Unit) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.scaffold_color),
        contentColor = Color.White,
        title = {
            Text(
                text = name
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onFinishClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_backarrow),
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        }
    )
}