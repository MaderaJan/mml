package cz.maderajan.mml.data.spotify

import cz.maderajan.mml.data.datastore.PreferencesDataStore

class SpotifyRepository(private val preferencesDataStore: PreferencesDataStore) {

    suspend fun persistAccessToken(token: String) {
        preferencesDataStore.storeSpotifyAccessToken(token)
    }
}