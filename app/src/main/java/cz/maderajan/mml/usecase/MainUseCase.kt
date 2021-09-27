package cz.maderajan.mml.usecase

import cz.maderajan.mml.data.datastore.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class MainUseCase(private val preferencesDataStore: PreferencesDataStore) {

    suspend fun isFirstSyncComplete(): Flow<Boolean> =
        preferencesDataStore.getFirstSyncComplete()
}