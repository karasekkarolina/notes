package cz.blackchameleon.notes

import android.app.Application
import cz.blackchameleon.notes.di.koinModule
import cz.blackchameleon.notes.di.useCasesModule
import cz.blackchameleon.notes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    koinModule, viewModelModule, useCasesModule
                )
            )
        }
    }
}
