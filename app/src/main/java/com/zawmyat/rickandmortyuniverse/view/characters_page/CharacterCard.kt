package com.zawmyat.rickandmortyuniverse.view.characters_page

import android.content.Context
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.FavCharacterObj
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.models.datastore.SortingStore
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel
import kotlinx.coroutines.launch

@Composable
fun CharacterCard(character: Character) {

    val favoriteCharacterViewModel : FavoriteCharacterViewModel = FavCharacterObj.favoriteCharacterViewModel

    val sortDataStore = SortingStore(AppContext.applicationContext)
    val currentSortValue = sortDataStore.sortValueFlow.collectAsState(initial = "ID (Default)")

    when {
        currentSortValue.value == "Name (Ascending)" -> {
            favoriteCharacterViewModel.orderByNameAsc()
        }
        currentSortValue.value == "Name (Descending)" -> {
            favoriteCharacterViewModel.orderByNameDesc()
        }
        else -> {
            favoriteCharacterViewModel.orderById()
        }
    }

    val favCharacters = favoriteCharacterViewModel.favChars.value

    val coroutineScope = rememberCoroutineScope()

    val favCharacter = remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.padding(bottom = 10.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.card_color))
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                )


                Spacer(modifier = Modifier.width(8.dp))

                Column {

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = character.name,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when {
                            character.status.equals("Alive") -> {
                                Box(
                                    modifier = Modifier
                                        .width(12.dp)
                                        .height(12.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Green),
                                )
                            }
                            character.status.equals("Dead") -> {
                                Box(
                                    modifier = Modifier
                                        .width(12.dp)
                                        .height(12.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Red),
                                )
                            }
                            else -> {
                                Box(
                                    modifier = Modifier
                                        .width(12.dp)
                                        .height(12.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Yellow),
                                )
                            }
                        }


                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${character.status} - ${character.species}",
                            fontSize = 16.sp,
                            color = Color.White,
                            maxLines = 1
                        )

                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_gender),
                            contentDescription = "gender",
                            tint = Color.White.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = character.gender,
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.7f),
                        )
                    }
                }
            }

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 10.dp, bottom = 8.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp)
                        .background(color = Color.White.copy(alpha = 0.8f))
                        .align(Alignment.BottomEnd)
                ) {

                    val favoriteCharacter = FavoriteCharacter(
                        name = character.name,
                        avatar = character.image,
                        status = character.status,
                        species = character.species,
                        gender = character.gender,
                        characterId = character.id
                    )

                    if(favCharacters.contains(favoriteCharacter)) {
                        favCharacter.value = true
                    } else {
                        favCharacter.value = false
                    }

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
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "fav",
                                tint = Color.Red
                            )
                        } else {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "fav",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}