package cz.maderajan.ui.spotifysync.di

import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
import cz.maderajan.ui.spotifysync.usecase.IntroSpotifyUseCase
import cz.maderajan.ui.spotifysync.usecase.SyncSpotifyAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
object SpotifySyncModule : KoinModule {

    private val module = module {
        viewModel { IntroSpotifySyncViewModel(get()) }
        viewModel { SelectSpotifyAlbumsViewModel(get()) }

        factory { IntroSpotifyUseCase(get(), get()) }
        factory { SyncSpotifyAlbumsUseCase(get(), get()) }
    }

    override fun startModule() {
        loadKoinModules(module)
    }
}