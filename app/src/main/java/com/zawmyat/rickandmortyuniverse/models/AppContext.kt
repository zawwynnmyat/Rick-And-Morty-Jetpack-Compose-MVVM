package com.zawmyat.rickandmortyuniverse.models

import android.content.Context

object AppContext {
    lateinit var applicationContext: Context

    var currentSortSetting = "ID (Default)"
}