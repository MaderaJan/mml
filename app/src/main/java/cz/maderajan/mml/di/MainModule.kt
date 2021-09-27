package cz.maderajan.mml.di

import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.mml.ui.viewmodel.MainActivityViewModel
import cz.maderajan.mml.usecase.MainUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
object MainModule : KoinModule {

    private val module = module {
        viewModel { MainActivityViewModel(get()) }

        factory { MainUseCase(get()) }
    }

    override fun startModule() {
        loadKoinModules(module)
    }
}