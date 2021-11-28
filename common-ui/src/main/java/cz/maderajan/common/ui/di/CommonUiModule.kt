package cz.maderajan.common.ui.di

import cz.maderajan.common.ui.NavigationFlowBus
import cz.maderajan.common.ui.Navigator
import cz.maderajan.common.ui.start.AppStartViewModel
import cz.maderajan.common.ui.usecase.AppStartUseCase
import cz.maderajan.mml.commonutil.KoinModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@ExperimentalCoroutinesApi
object CommonUiModule : KoinModule {

    private val module = module {
        viewModel { AppStartViewModel(get(), get()) }

        factory { AppStartUseCase(get()) }

        single { Navigator() }
        single { NavigationFlowBus() }
    }

    override fun startModule() {
        loadKoinModules(module)
    }
}