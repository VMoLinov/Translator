package mvs.translator

import android.app.Application
import mvs.translator.koin.application
import mvs.translator.koin.mainScreen
import mvs.translator.model.data.db.DatabaseModel
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
        instance = this
        DatabaseModel.create(instance)
    }

    companion object {
        lateinit var instance: Application
    }
}
