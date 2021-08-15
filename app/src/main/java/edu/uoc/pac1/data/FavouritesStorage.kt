package edu.uoc.pac1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by alex on 15/8/21.
 */

private const val bookFavouritesDataStoreName = "bookFavouritesDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = bookFavouritesDataStoreName)

// This class helps storing and retrieving the ids of books that have been marked as favourite
class FavouritesStorage(private val context: Context) {

    private val bookFavouriteIdsKey = "bookFavouriteIds"
    private val bookFavouritesPreferencesKey = stringSetPreferencesKey(bookFavouriteIdsKey)

    // Get Flow of favourites (will emit a new value when favourites change)
    val favouritesList: Flow<List<Int>> = context.dataStore.data
        .map { preferences ->
            (preferences[bookFavouritesPreferencesKey]?.mapNotNull { it.toIntOrNull() }
                ?: emptySet()).toList()
        }

    // Sets a book as favourite
    suspend fun setFavourite(bookId: Int) {
        context.dataStore.edit { settings ->
            val currentCounterValue = settings[bookFavouritesPreferencesKey]
            settings[bookFavouritesPreferencesKey] =
                (currentCounterValue ?: emptySet()).plus(bookId.toString())
        }
    }

    // Removes a book from favourites
    suspend fun removeFavourite(bookId: Int) {
        context.dataStore.edit { settings ->
            val currentCounterValue = settings[bookFavouritesPreferencesKey]
            settings[bookFavouritesPreferencesKey] =
                (currentCounterValue ?: emptySet()).minus(bookId.toString())
        }
    }

}