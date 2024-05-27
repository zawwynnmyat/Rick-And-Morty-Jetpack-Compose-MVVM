package com.zawmyat.rickandmortyuniverse.view.characters_page

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun AppBarCharacters(onSearchClick : () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.characters))
        },
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search_chars))
            }
        },
        modifier = Modifier.background(colorResource(id = R.color.scaffold_color)),
        backgroundColor = colorResource(id = R.color.scaffold_color),
        contentColor = Color.White
    )
}