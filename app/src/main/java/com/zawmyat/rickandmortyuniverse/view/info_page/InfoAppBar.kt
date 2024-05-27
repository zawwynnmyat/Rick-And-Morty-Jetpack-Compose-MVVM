package com.zawmyat.rickandmortyuniverse.view.info_page

import android.content.res.Resources.Theme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun InfoAppBar(onShareClick : () -> Unit) {
    TopAppBar (
        title = {
            Text(
                text = "About This App",
                color = Color.White,
            )
        },
        actions = {
            IconButton(
                onClick = onShareClick
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "share",
                    tint = Color.White
                )
            }
        },
        backgroundColor = colorResource(id = R.color.scaffold_color)
    )
}