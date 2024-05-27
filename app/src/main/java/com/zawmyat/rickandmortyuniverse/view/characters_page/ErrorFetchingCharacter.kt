package com.zawmyat.rickandmortyuniverse.view.characters_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun ErrorFetchingCharacters(error: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "error",
            tint = Color.Red,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = error,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}