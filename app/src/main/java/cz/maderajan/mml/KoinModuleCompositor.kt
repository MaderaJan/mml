package cz.maderajan.mml

import android.content.Context
import cz.maderajan.common.ui.di.CommonUiModule
import cz.maderajan.mml.commonutil.KoinModule
import cz.maderajan.mml.data.di.DataModule
import cz.maderajan.ui.spotifysync.di.SpotifySyncModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

object KoinModuleCompositor {

    private val koinModules: List<KoinModule>
        get() = listOf(
            SpotifySyncModule,
            DataModule,
            CommonUiModule
        )

    fun loadKoinModules(context: Context) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(context)
        }

        koinModules.forEach(KoinModule::startModule)
    }
}