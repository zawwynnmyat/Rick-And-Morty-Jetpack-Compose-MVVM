package com.zawmyat.rickandmortyuniverse.view.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp



@Composable
fun CharactersShimmer() {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemCount = 10

    Column(
    ) {
        for(i in 0..itemCount) {
            Box(modifier = Modifier.padding(bottom = 10.dp)) {

                Row {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 10.dp))
                            .height(120.dp)
                            .width(120.dp)
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Spacer(modifier = Modifier.height(5.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(size = 5.dp))
                                .fillMaxWidth()
                                .height(22.dp)
                                .background(color = Color.LightGray)
                                .shimmerLoadingAnimation()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(size = 5.dp))
                                .width(screenWidth / 2)
                                .height(22.dp)
                                .background(color = Color.LightGray)
                                .shimmerLoadingAnimation()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(size = 5.dp))
                                .width(screenWidth/3)
                                .height(22.dp)
                                .background(color = Color.LightGray)
                                .shimmerLoadingAnimation()
                        )

                    }
                }
            }
        }
    }
}

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 700,
) : Modifier {
    return composed {
        val shimmerColors = listOf(
            Color.Black.copy(alpha = 0.3f),
            Color.Black.copy(alpha = 0.5f),
            Color.Black.copy(alpha = 1.0f),
            Color.Black.copy(alpha = 0.5f),
            Color.Black.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "Shimmer loading animation"
        )
        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            )
        )
    }
}