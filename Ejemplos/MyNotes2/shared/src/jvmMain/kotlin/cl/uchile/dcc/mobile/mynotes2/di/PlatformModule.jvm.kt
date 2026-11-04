package cl.uchile.dcc.mobile.mynotes2.di

import cl.uchile.dcc.mobile.mynotes2.data.local.getDatabaseBuilder
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationProvider
import cl.uchile.dcc.mobile.mynotes2.ui.components.MapHandler
import org.koin.dsl.module

val jvmPlatformModule = module {
    single {
        getDatabaseBuilder()
    }

    single {
        LocationProvider()
    }

    single {
        MapHandler()
    }
}