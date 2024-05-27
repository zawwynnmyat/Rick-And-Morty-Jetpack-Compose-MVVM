package com.zawmyat.rickandmortyuniverse.view.characters_page.character_search

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.viewmodel.SearchCharacterViewModel

@Composable
fun SearchScaffold(
    viewModel: SearchCharacterViewModel = hiltViewModel(),
    onFinishClicked: () -> Unit,
    context: Context
) {

    val searchQuery by viewModel.queryFlow.collectAsState()
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    Surface {
        Scaffold(
            backgroundColor = colorResource(id = R.color.scaffold_color),
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    SearchBarCharacters(
                        query = searchQuery,
                        onQueryChange = viewModel::onSearchQueryChange,
                        onFinishClicked = onFinishClicked,
                        viewModel = viewModel,
                    )
                }

                if(searchQuery.isEmpty()) {
                    SearchPrompt()
                } else {
                    if(searchResults.itemCount == 0) {
                        EmptySearchResults()
                    } else {
                        CharacterSearchResults(
                            context = context,
                            searchResults = searchResults
                        )
                    }
                }
            }
        }
    }
}
