package cz.maderajan.mml

import android.app.Application
import timber.log.Timber

class MmlApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        KoinModuleCompositor.loadKoinModules(applicationContext)
    }
}