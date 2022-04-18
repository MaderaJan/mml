package cz.maderajan.uialbums.usecase

import cz.maderajan.mml.data.repository.album.AlbumRepository

class AlbumListUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun getAlbums() {
        albumRepository.getAllAlbums()
    }
}