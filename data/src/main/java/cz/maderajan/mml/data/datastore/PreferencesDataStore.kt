package cz.maderajan.mml.data.datastore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import cz.maderajan.mml.data.datastore.PreferencesDataStore.PreferenceKey.SPOTIFY_ACCESS_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val dataStore: DataStore<Preferences>) {

    private object PreferenceKey {
        val SPOTIFY_ACCESS_TOKEN = preferencesKey<String>("SPOTIFY_ACCESS_TOKEN")
    }

    suspend fun getSpotifyAccessToken(): Flow<String> = dataStore.getFromLocalStorage(SPOTIFY_ACCESS_TOKEN)

    suspend fun getSpotifyBearerAccessToken(): String = getSpotifyAccessToken().map { "Bearer $it" }.first()

    suspend fun storeSpotifyAccessToken(token: String) =
        dataStore.addToLocalStorage {
            this[SPOTIFY_ACCESS_TOKEN] = token
        }
}