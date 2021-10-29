package cz.maderajan.common.ui.usecase

import cz.maderajan.mml.data.datastore.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class AppStartUseCase(private val preferencesDataStore: PreferencesDataStore) {

    suspend fun isFirstSyncComplete(): Flow<Boolean> =
        preferencesDataStore.getFirstSyncComplete()
}