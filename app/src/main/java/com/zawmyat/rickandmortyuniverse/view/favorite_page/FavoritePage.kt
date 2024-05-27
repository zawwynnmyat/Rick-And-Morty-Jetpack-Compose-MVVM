package com.zawmyat.rickandmortyuniverse.view.favorite_page

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.FavCharacterObj
import com.zawmyat.rickandmortyuniverse.models.datastore.SortingStore
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import com.zawmyat.rickandmortyuniverse.view.characters_page.CharacterDetailsActivity
import com.zawmyat.rickandmortyuniverse.viewmodel.FavoriteCharacterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritePage(navController: NavController) {

    val favoriteCharacterViewModel : FavoriteCharacterViewModel = FavCharacterObj.favoriteCharacterViewModel

    val favCharacters = favoriteCharacterViewModel.favChars.value

    val sortDataStore = SortingStore(AppContext.applicationContext)
//    val currentSortValue = sortDataStore.sortValueFlow.collectAsState(initial = "ID (Default)")

    val lazyListState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    var showSheet by remember {
        mutableStateOf(false)
    }

    val currentSortOption = rememberSaveable {
        mutableStateOf("ID (Default)")
    }

    Log.i("currentSortValue", AppContext.currentSortSetting)

    currentSortOption.value = AppContext.currentSortSetting

    if(showSheet) {
        SortOptionsSheet(
            favoriteCharacterViewModel = favoriteCharacterViewModel,
            onDismiss = {
                returnedSortOption ->
                     showSheet = false
                CoroutineScope(Dispatchers.IO).launch {
                    sortDataStore.updateSortValue(returnedSortOption)
                    currentSortOption.value = returnedSortOption
                }
            },
            currentSortOption = currentSortOption.value,
        )
    }

    Surface {
        Scaffold(
            topBar = {
                FavAppBar(
                    onSortIconClick = {
                        showSheet = true
                    },
                    showFilterButton = if(favCharacters.size > 0) true else false
                )
            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.scaffold_color))
                    .padding(it)
            ) {

                favCharacters?.let {

                    if(favCharacters.size > 0) {

                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(110.dp),
                            contentPadding = PaddingValues(all = 8.dp),
                            content = {
                                items(favCharacters) {
                                    //Fav Content
                                    Box(
                                        modifier = Modifier
                                            .padding(all = 8.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                            .background(color = colorResource(id = R.color.card_color))
                                            .clickable(
                                                onClick = {
                                                    val intent = Intent(
                                                        AppContext.applicationContext,
                                                        CharacterDetailsActivity::class.java
                                                    ).apply {
                                                        putExtra("name", it.name)
                                                        putExtra("id", it.characterId)
                                                    }
                                                    AppContext.applicationContext.startActivity(
                                                        intent
                                                    )
                                                }
                                            )
                                    ) {
                                        Column {

                                            AsyncImage(
                                                model = it.avatar,
                                                contentDescription = "avatar",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .clip(
                                                        shape = RoundedCornerShape(
                                                            topEnd = 8.dp,
                                                            topStart = 8.dp
                                                        )
                                                    )
                                            )

                                            Column(
                                                modifier = Modifier.padding(all = 5.dp)
                                            ) {
                                                Text(
                                                    text = it.name ?: "",
                                                    color = Color.White,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )


                                            }

                                        }


                                    }


                                    //Heart Button
                                    Box(
                                        modifier = Modifier
                                            .padding(all = 8.dp)
                                            .fillMaxSize()
                                            .clip(shape = RoundedCornerShape(8.dp))
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .clip(shape = CircleShape)
                                                .size(30.dp)
                                                .background(color = colorResource(id = R.color.white))
                                                .align(Alignment.TopEnd)
                                        ) {
                                            val favoriteCharacter = FavoriteCharacter(
                                                name = it.name,
                                                avatar = it.avatar,
                                                status = it.status,
                                                species = it.species,
                                                gender = it.gender,
                                                characterId = it.characterId
                                            )

                                            androidx.compose.material3.IconButton(
                                                onClick = {
                                                    if (favCharacters.contains(favoriteCharacter)) {
                                                        favoriteCharacterViewModel.removeFav(favoriteCharacter)
                                                    } else {
                                                        coroutineScope.launch {
                                                            favoriteCharacterViewModel.addToFav(favoriteCharacter)
                                                        }
                                                    }
                                                }
                                            ) {
                                                if (favCharacters.contains(favoriteCharacter)) {
                                                    androidx.compose.material3.Icon(
                                                        imageVector = Icons.Default.Favorite,
                                                        contentDescription = "fav",
                                                        tint = Color.Red,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                } else {
                                                    androidx.compose.material3.Icon(
                                                        imageVector = Icons.Default.FavoriteBorder,
                                                        contentDescription = "fav",
                                                        tint = Color.Red,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }


                                }
                            }
                        )

                    } else {

                        EmptyFavorite()

                    }


                }

            }
        }
    }
}


fun handleSortOption(
    sortOption: String,
    favoriteCharacterViewModel: FavoriteCharacterViewModel,
    onDismiss: () -> Unit,
) {

    val options = listOf("Name (Ascending)", "Name (Descending)", "ID (Default)")

    when {
        sortOption == options.first() -> {
            favoriteCharacterViewModel.orderByNameAsc()
        }
        sortOption == options[1] -> {
            favoriteCharacterViewModel.orderByNameDesc()
        }
        else -> {
            favoriteCharacterViewModel.orderById()
        }
    }
    onDismiss()


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortOptionsSheet(
    onDismiss: (sortOption: String) -> Unit,
    favoriteCharacterViewModel: FavoriteCharacterViewModel,
    currentSortOption: String,
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    val sortOptions = listOf("Name (Ascending)", "Name (Descending)", "ID (Default)")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(currentSortOption)
    }


    ModalBottomSheet(
        onDismissRequest = { onDismiss(selectedOption) },
        sheetState = modalBottomSheetState,
        containerColor = colorResource(id = R.color.episode_color),
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        },
    ) {
        Column(
            modifier = Modifier.padding(all = 10.dp)
        ) {
            Text(
                text = "Sort By ...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(25.dp))

            Column(Modifier.selectableGroup()) {
                sortOptions.forEach {
                    text ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .align(Alignment.CenterHorizontally)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                    handleSortOption(
                                        sortOption = text,
                                        favoriteCharacterViewModel = favoriteCharacterViewModel,
                                        onDismiss = { onDismiss(text) },
                                    )
                                },
                                role = Role.RadioButton
                            ),
                        backgroundColor = colorResource(id = R.color.card_color)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, top = 15.dp, bottom = 15.dp)
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = text,
                                fontSize = 18.sp,
                                color = if(text == selectedOption)
                                    colorResource(id = R.color.purple_200)
                                else
                                    Color.White
                            )
                        }

                    }
                }
            }

        }
        
        Spacer(modifier = Modifier.height(120.dp))
    }
}



