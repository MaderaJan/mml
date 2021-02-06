package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.TokenRepository

class IntroSpotifyUseCase(private val tokenRepository: TokenRepository) {

    suspend fun persistSpotifyAccessToken(token: String) {
        tokenRepository.persistSpotifyAccessToken(token)
    }

    suspend fun getSpotifyAccessToken(): Boolean =
        tokenRepository.isUserLoggedInSpotifyAccount()
}