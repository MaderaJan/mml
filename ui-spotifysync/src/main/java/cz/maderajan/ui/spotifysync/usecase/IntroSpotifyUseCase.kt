package cz.maderajan.ui.spotifysync.usecase

import cz.maderajan.mml.data.spotify.SpotifyRepository

class IntroSpotifyUseCase(private val spotifyRepository: SpotifyRepository) {

    suspend fun persistSpotifyAccessToken(token: String) {
        spotifyRepository.persistAccessToken(token)
    }
}