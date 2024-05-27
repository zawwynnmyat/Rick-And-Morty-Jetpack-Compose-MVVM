package com.zawmyat.rickandmortyuniverse.models.data_classes.character

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)