package com.zawmyat.rickandmortyuniverse.view.characters_page.character_search

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.zawmyat.rickandmortyuniverse.view.characters_page.CharacterCard
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.view.characters_page.CharacterDetailsActivity

@Composable
fun CharacterSearchResults(context: Context, searchResults: LazyPagingItems<Character>) {
    LazyColumn(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        items(count = searchResults.itemCount) {
                index ->
            searchResults[index]?.let {
                    chara ->
                Box(
                    modifier = Modifier.clickable(
                    onClick = {
                        val intent = Intent(context, CharacterDetailsActivity::class.java).apply {
                            putExtra("name", chara.name)
                            putExtra("id", chara.id)
                        }
                        context.startActivity(intent)
                    }
                )) {
                    CharacterCard(character = chara)
                }


            }
        }
    }
}