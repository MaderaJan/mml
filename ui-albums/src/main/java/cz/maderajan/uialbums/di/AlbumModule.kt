package cz.maderajan.uialbums.di

import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.uialbums.ui.list.AlbumListViewModel
import cz.maderajan.uialbums.usecase.AlbumListUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AlbumModule : KoinModule {

    private val module = module {
        viewModel { AlbumListViewModel(get()) }

        factory { AlbumListUseCase(get()) }
    }

    override fun startModule() {
        loadKoinModules(module)
    }
}