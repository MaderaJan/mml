package cz.maderajan.mml.data.datastore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import cz.maderajan.mml.data.data.exceptions.NetworkBaseException
import cz.maderajan.mml.data.datastore.PreferencesDataStore.PreferenceKey.FIRST_SYNC_COMPLETE
import cz.maderajan.mml.data.datastore.PreferencesDataStore.PreferenceKey.SPOTIFY_ACCESS_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val dataStore: DataStore<Preferences>) {

    private object PreferenceKey {
        val SPOTIFY_ACCESS_TOKEN = preferencesKey<String>("SPOTIFY_ACCESS_TOKEN")
        val FIRST_SYNC_COMPLETE = preferencesKey<Boolean>("FIRST_SYNC_COMPLETE")
    }

    suspend fun getSpotifyAccessToken(): String? = dataStore.getFromLocalStorage(SPOTIFY_ACCESS_TOKEN).first()

    suspend fun getSpotifyBearerAccessToken(): String =
        getSpotifyAccessToken()?.let { "Bearer $it" } ?: throw NetworkBaseException.UnauthorizedException

    suspend fun storeSpotifyAccessToken(token: String) {
        dataStore.addToLocalStorage {
            this[SPOTIFY_ACCESS_TOKEN] = token
        }
    }

    suspend fun setFirstSyncComplete() {
        dataStore.addToLocalStorage {
            this[FIRST_SYNC_COMPLETE] = true
        }
    }

    suspend fun getFirstSyncComplete(): Flow<Boolean> =
        dataStore.getFromLocalStorage(FIRST_SYNC_COMPLETE)
            .map {
                it ?: false
            }

}