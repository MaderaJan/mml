package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.repository.auth.TokenRepository
import cz.maderajan.mml.data.repository.spotify.SpotifyRepository

class IntroSpotifyUseCase(private val tokenRepository: TokenRepository, private val spotifyRepository: SpotifyRepository) {

    suspend fun persistSpotifyAccessToken(token: String) {
        tokenRepository.persistSpotifyAccessToken(token)
    }

    suspend fun getSpotifyAccessToken(): Boolean =
        tokenRepository.isUserLoggedInSpotifyAccount()

    suspend fun synchronizationSkipped() {
        spotifyRepository.firstSyncComplete()
    }
}