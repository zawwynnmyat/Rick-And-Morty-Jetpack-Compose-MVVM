package com.zawmyat.rickandmortyuniverse.view.favorite_page

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
fun FavAppBar(onSortIconClick: () -> Unit, showFilterButton: Boolean) {
    TopAppBar(
        title = {
            Text(
                text = "Favorite",
                color = Color.White
            )
        },
        backgroundColor = colorResource(id = R.color.scaffold_color),
        actions = {

            if(showFilterButton)
            IconButton(
                onClick = onSortIconClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = "sort",
                    tint = Color.White
                )
            }
        }
    )
}
