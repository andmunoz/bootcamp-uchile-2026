package cl.uchile.dcc.mobile.mynotes2.di

import cl.uchile.dcc.mobile.mynotes2.data.local.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidPlatformModule = module {
    single {
        getDatabaseBuilder(androidContext())
    }
}