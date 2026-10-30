package cl.uchile.dcc.mobile.mynotes2.di

import cl.uchile.dcc.mobile.mynotes2.data.local.getDatabaseBuilder
import org.koin.dsl.module

val jvmPlatformModule = module {
    single {
        getDatabaseBuilder()
    }
}