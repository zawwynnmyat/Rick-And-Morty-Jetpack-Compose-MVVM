package com.zawmyat.rickandmortyuniverse.view.characters_page


import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.FavCharacterObj
import com.zawmyat.rickandmortyuniverse.models.datastore.SortingStore
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import com.zawmyat.rickandmortyuniverse.view.shimmer.CharacterDetailsShimmer
import com.zawmyat.rickandmortyuniverse.view.shimmer.shimmerLoadingAnimation
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel
import com.zawmyat.rickandmortyuniverse.viewmodel.SingleCharacterViewModel
import kotlinx.coroutines.launch



@Composable
fun CharacterDetails(name: String, id: Int, onFinishClicked: () -> Unit) {

    val singleCharacterViewModel : SingleCharacterViewModel = hiltViewModel<SingleCharacterViewModel>()

    val singleCharacter by singleCharacterViewModel.singleCharacter.observeAsState()
    val isFetchingCharacter by singleCharacterViewModel.isFetchingCharacter.observeAsState(initial = false)
    val fetchErrorMessage by singleCharacterViewModel.fetchErrorMessage.observeAsState(initial = "")

    LaunchedEffect(Unit) {
        singleCharacterViewModel.getSingleCharacter(id)
    }

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    val scrollState = rememberScrollState()

    val favCharacter = remember {
        mutableStateOf(false)
    }

    val favoriteCharacterViewModel : FavoriteCharacterViewModel = FavCharacterObj.favoriteCharacterViewModel

    val favCharacters = favoriteCharacterViewModel.favChars.value

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CharacterDetailsAppBar(
                name = name,
                onFinishClicked = onFinishClicked
            )
        },

        ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.scaffold_color))
                .verticalScroll(scrollState)
        ) {

            Log.i("loading", isFetchingCharacter.toString())

            if(isFetchingCharacter) {
                CharacterDetailsShimmer()
            } else if (fetchErrorMessage != "") {
                ErrorCharacterDetails(error = fetchErrorMessage)
            } else {
                singleCharacter?.let {

                    val favoriteCharacter = FavoriteCharacter(
                        name = it.name,
                        avatar = it.image,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        characterId = it.id
                    )

                    if(favCharacters.contains(favoriteCharacter)) {
                        favCharacter.value = true
                    } else {
                        favCharacter.value = false
                    }

                    Card(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .shadow(elevation = 10.dp, ambientColor = Color.White),
                        colors = CardColors(
                            containerColor = colorResource(id = R.color.card_color),
                            contentColor = colorResource(id = R.color.white),
                            disabledContainerColor = colorResource(id = R.color.card_color),
                            disabledContentColor = colorResource(id = R.color.white),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp,
                            disabledElevation = 10.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(screenWidth.div(2.5.dp).dp)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = Color.White)
                                ) {
                                    AsyncImage(
                                        model = it.image,
                                        contentDescription = "avatar",
                                        modifier = Modifier
                                            .height(screenWidth.div(2.5.dp).dp)
                                            .clip(shape = RoundedCornerShape(10.dp))
                                    )
                                }

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
                                        Text(
                                            text = "Name",
                                            color = Color.White.copy(alpha = 0.5f),
                                            fontSize = 16.sp
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = it.name,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }


                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.End)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(40.dp)
                                                .background(color = Color.White)
                                                .align(Alignment.TopEnd)
                                        ) {
                                            IconButton(
                                                onClick = {
                                                    if(favCharacter.value) {
                                                        favoriteCharacterViewModel.removeFav(favoriteCharacter)
                                                        favCharacter.value = false
                                                    } else {
                                                        coroutineScope.launch {
                                                            favoriteCharacterViewModel.addToFav(favoriteCharacter)
                                                        }
                                                        favCharacter.value = true
                                                    }
                                                }
                                            ) {
                                                if(favCharacter.value) {
                                                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav", tint = Color.Red)
                                                } else {
                                                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "fav", tint = Color.Red)
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Divider(
                                color = Color.White.copy(alpha = 0.3f)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            PropertyBlock(
                                title = "Status",
                                data = it.status,
                                icon = R.drawable.ic_heart_monitor
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Species",
                                data = it.species,
                                icon = R.drawable.ic_species
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Type",
                                data = it.type,
                                icon = R.drawable.ic_category
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Gender",
                                data = it.gender,
                                icon = R.drawable.ic_gender
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Origin",
                                data = it.origin.name,
                                icon = R.drawable.ic_origin
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Location",
                                data = it.location.name,
                                icon = R.drawable.ic_location
                            )

                            Spacer(modifier = Modifier.height(13.dp))

                            PropertyBlock(
                                title = "Episode Played",
                                data = "${it.episode.size} Episodes",
                                icon = R.drawable.ic_movie
                            )
                        }
                    }
                }
            }
        }
    }
}








