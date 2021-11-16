package mvs.translator

import android.app.Application
import mvs.translator.koin.application
import mvs.translator.koin.descriptionScreen
import mvs.translator.koin.historyScreen
import mvs.translator.koin.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen, descriptionScreen))
        }
    }
}
