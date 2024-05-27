package com.zawmyat.rickandmortyuniverse.view.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.zawmyat.rickandmortyuniverse.view.shimmer.shimmerLoadingAnimation

@Composable
fun LocationShimmer() {

    val screenWidth = LocalConfiguration.current.screenWidthDp

    Column() {

        for(i in 1..10) {
            Column {

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(22.dp)
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .width(screenWidth.div(1.7).dp)
                        .height(22.dp)
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .width(screenWidth.div(3).dp)
                        .height(22.dp)
                        .background(color = Color.LightGray)
                        .shimmerLoadingAnimation()
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}
