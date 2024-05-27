package com.zawmyat.rickandmortyuniverse.view.characters_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PropertyBlock(title: String, data: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White.copy(0.7f)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            when {
                data != "" -> {
                    Text(
                        text = data,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                else -> {
                    Text(
                        text = "-",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}