package com.zawmyat.rickandmortyuniverse.view.episodes_page

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.data_classes.episode.Episode
import com.zawmyat.rickandmortyuniverse.view.locations_page.ErrorFetchingCharacters
import com.zawmyat.rickandmortyuniverse.view.locations_page.LocationAppBar
import com.zawmyat.rickandmortyuniverse.view.locations_page.LocationListTile
import com.zawmyat.rickandmortyuniverse.view.shimmer.LocationShimmer
import com.zawmyat.rickandmortyuniverse.viewmodel.EpisodesViewModel
import com.zawmyat.rickandmortyuniverse.viewmodel.LocationViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodesPage() {
    val episodeViewModel : EpisodesViewModel = hiltViewModel<EpisodesViewModel>()
    val episodes = episodeViewModel.getEpisodes().collectAsLazyPagingItems()

    val covers = listOf(
        R.drawable.cover1,
        R.drawable.cover2,
        R.drawable.cover3,
        R.drawable.cover4,
        R.drawable.cover5,
        R.drawable.cover6,
        R.drawable.cover7
    )

    Scaffold(
        topBar = {
            EpisodeAppBar()
        },
        backgroundColor = colorResource(id = R.color.scaffold_color)
    ) {

        LazyColumn(
            modifier = Modifier.padding(
                all = 10.dp
            )
        ) {

            items(
                episodes.itemCount
            ) {
                    index ->
                episodes[index]?.let {
                    EpisodeListTile(episode = it, cover = covers[index % 7])
                }
            }

            episodes.apply {
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
                            ErrorFetchingCharacters(error = "Something went wrong while fetching episodes")
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