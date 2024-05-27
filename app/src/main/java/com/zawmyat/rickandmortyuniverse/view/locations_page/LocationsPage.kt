package com.zawmyat.rickandmortyuniverse.view.locations_page

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.data_classes.location.Location
import com.zawmyat.rickandmortyuniverse.view.shimmer.LocationShimmer
import com.zawmyat.rickandmortyuniverse.viewmodel.LocationViewModel


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LocationsPage(navController: NavController) {

    val locationViewModel : LocationViewModel = hiltViewModel<LocationViewModel>()
    val locations = locationViewModel.getLocations().collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            LocationAppBar(

            )
        },
        backgroundColor = colorResource(id = R.color.scaffold_color)
    ) {

        LazyColumn(
            modifier = Modifier.padding(
                all = 10.dp
            )
        ) {

            items(
                locations.itemCount
            ) {
                    index ->
                locations[index]?.let {
                    LocationListTile(location = it)
                }
            }

            locations.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {

                                LocationShimmer()

                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item { 
                            ErrorFetchingCharacters(error = "Something went wrong while fetching locations")
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {

                                CircularProgressIndicator(color = Color.White)
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {

                    }
                }
            }
        }

    }
}

@Composable
fun LocationListTile(location: Location) {
    Box(modifier = Modifier.padding(bottom = 10.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 10.dp))
            .background(color = Color.Green)
            .clip(RoundedCornerShape(size = 10.dp))
        ) {
            Box(modifier = Modifier
                .padding(start = 6.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 10.dp))
                .background(color = colorResource(id = R.color.card_color))
                .clip(RoundedCornerShape(size = 10.dp))
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = location.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Divider(color = Color.White.copy(alpha = 0.2f))

                    Spacer(modifier = Modifier.height(5.dp))

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_character),
                            contentDescription = "residents",
                            tint = Color.White.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        if(location.residents.size == 0) {
                            Text(
                                text = "No Resident",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 16.sp
                            )
                        }

                        if(location.residents.size > 1) {
                            Text(
                                text = "${location.residents.size} Residents",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 16.sp
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            ) {
                                append(text = "Type : ")
                            }

                            withStyle(
                                SpanStyle(
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 16.sp
                                )
                            ) {
                                append(text = location.type)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            ) {
                                append(text = "Dimension : ")
                            }

                            withStyle(
                                SpanStyle(
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 16.sp
                                )
                            ) {
                                append(text = location.dimension)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LocationAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "All Locations",
                color = Color.White
            )
        },
        backgroundColor = colorResource(id = R.color.scaffold_color),
    )
}


