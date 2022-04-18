package cz.maderajan.mml.data.repository.auth

import cz.maderajan.mml.data.datastore.PreferencesDataStore

class TokenRepository(private val preferencesDataStore: PreferencesDataStore) {

    suspend fun persistSpotifyAccessToken(token: String) {
        preferencesDataStore.storeSpotifyAccessToken(token)
    }

    suspend fun isUserLoggedInSpotifyAccount(): Boolean =
        preferencesDataStore.getSpotifyAccessToken() != null

    suspend fun getSpotifyAccessToken(): String =
        preferencesDataStore.getSpotifyBearerAccessToken()
}