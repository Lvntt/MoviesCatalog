package ru.lantt.moviescatalog

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.lantt.moviescatalog.di.provideDomainModule
import ru.lantt.moviescatalog.di.providePresentationModule

class MoviesCatalogApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesCatalogApplication)
            modules(
                provideDomainModule(),
                providePresentationModule()
            )
        }
    }

}