package com.zawmyat.rickandmortyuniverse.models.data_classes.location

data class LocationResponse(
    val info: Info,
    val results: List<Location>
)