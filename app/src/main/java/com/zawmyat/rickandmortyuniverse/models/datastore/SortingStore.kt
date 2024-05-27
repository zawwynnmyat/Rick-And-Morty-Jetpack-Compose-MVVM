package com.zawmyat.rickandmortyuniverse.models.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SortingStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sort_favorite")
        private val sortKey = stringPreferencesKey("sort_key")

    }

    val sortValueFlow: Flow<String> = context.dataStore.data.map {
        preferences ->
          preferences[sortKey] ?: "ID (Default)"
    }

    suspend fun updateSortValue(sortValue: String) {
        context.dataStore.edit {
            preferences ->
            preferences[sortKey] = sortValue
        }
    }


}