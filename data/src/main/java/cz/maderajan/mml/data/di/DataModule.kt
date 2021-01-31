package cz.maderajan.mml.data.di

import androidx.datastore.preferences.createDataStore
import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.mml.data.datastore.PreferencesDataStore
import cz.maderajan.mml.webservice.di.WebServiceModule
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DataModule : KoinModule {

    private val module = module {
        single(createdAtStart = true) { PreferencesDataStore(androidApplication().createDataStore(name = "mmml.datastore")) }
    }

    override fun startModule() {
        loadKoinModules(listOf(module, RepositoryModule.module, MapperModule.module, WebServiceModule.module))
    }
}