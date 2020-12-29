package cz.maderajan.ui.spotifysync.di

import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.ui.spotifysync.intro.IntroSpotifySyncViewModel
import cz.maderajan.ui.spotifysync.usecase.IntroSpotifyUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object SpotifySyncModule : KoinModule {

    private val module = module {
        viewModel { IntroSpotifySyncViewModel(get()) }

        single { IntroSpotifyUseCase(get()) }
    }

    override fun startModule() {
        loadKoinModules(module)
    }
}