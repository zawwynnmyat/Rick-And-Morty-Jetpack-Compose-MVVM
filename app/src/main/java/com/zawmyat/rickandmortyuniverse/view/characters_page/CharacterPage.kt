package com.zawmyat.rickandmortyuniverse.view.characters_page

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.viewmodel.CharactersViewModel
import androidx.compose.ui.platform.LocalContext
import com.zawmyat.rickandmortyuniverse.view.shimmer.CharactersShimmer

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharactersPage(navController : NavController) {

    val cviewModel : CharactersViewModel = hiltViewModel<CharactersViewModel>()
    val characters = cviewModel.getCharacters().collectAsLazyPagingItems()

    val context = LocalContext.current

    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            AppBarCharacters(
                onSearchClick = {
                    val intent = Intent(context, SearchCharactersActivity::class.java)
                    context.startActivity(intent)
                }
            )
        },
        backgroundColor = colorResource(id = R.color.scaffold_color)
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                all = 10.dp
            ),
            state = lazyListState
        ) {

            items(
                characters.itemCount
            ) {
                    index ->
                characters[index]?.let {
                    Box(modifier = Modifier.clickable(
                        onClick = {
                            val intent = Intent(context, CharacterDetailsActivity::class.java).apply {
                                putExtra("name", it.name)
                                putExtra("id", it.id)
                            }
                            context.startActivity(intent)
                        }
                    )) {
                        CharacterCard(character = it)
                    }
                }
            }

            characters.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CharactersShimmer()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            ErrorFetchingCharacters(error = "Something went wrong while fetching characters")
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
//                        item {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.Center,
//                            ) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.ic_error),
//                                    contentDescription = "error",
//                                    tint = Color.Red
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}



