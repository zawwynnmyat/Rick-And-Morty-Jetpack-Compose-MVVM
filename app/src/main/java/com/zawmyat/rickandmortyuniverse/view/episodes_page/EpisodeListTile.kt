package com.zawmyat.rickandmortyuniverse.view.episodes_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.data_classes.episode.Episode

@Composable
fun EpisodeListTile(episode: Episode, cover: Int) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(bottom = 13.dp),
        contentAlignment = Alignment.BottomStart
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(140.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.episode_color)),

            ) {

            Row(
                modifier = Modifier.padding(bottom = 5.dp, top = 5.dp)
            ) {
                Box(modifier = Modifier.width(140.dp))

                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = episode.episode,
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = episode.name,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(17.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = episode.air_date,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 15.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(17.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${episode.characters.size} Characters",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 15.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }
            }
        }


        Box(modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(180.dp)
                    .width(120.dp)
            ) {
                AsyncImage(
                    model = cover,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

        }


    }
}