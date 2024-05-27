package com.zawmyat.rickandmortyuniverse.view.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun CharacterDetailsShimmer() {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(screenWidth.div(2.5.dp).dp)
                    .width(screenWidth.div(2.5.dp).dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .shimmerLoadingAnimation()
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.height(screenWidth.div(2.5.dp).dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                //Name
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .width(screenWidth.div(4))
                            .height(22.dp)
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .fillMaxWidth()
                            .height(22.dp)
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )
                }



            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            color = Color.White.copy(alpha = 0.0f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column {

            for(i in 1..6) {
                Row {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .width(55.dp)
                            .height(55.dp)
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .width(screenWidth.div(4))
                                .height(22.dp)
                                .background(color = Color.LightGray)
                                .shimmerLoadingAnimation()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .fillMaxWidth()
                                .height(22.dp)
                                .background(color = Color.LightGray)
                                .shimmerLoadingAnimation()
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }

    }
}