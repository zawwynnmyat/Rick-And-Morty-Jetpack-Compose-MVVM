package com.zawmyat.rickandmortyuniverse.models.data_classes.episode

data class EpisodeResponse(
    val info: Info,
    val results: List<Episode>
)