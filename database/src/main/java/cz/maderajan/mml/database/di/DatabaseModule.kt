package cz.maderajan.mml.database.di

import cz.maderajan.mml.database.MmlDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {

    val module = module {
        single { MmlDatabase.create(androidContext()) }

        factory { get<MmlDatabase>().albumDao() }
    }
}