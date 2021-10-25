package mvs.translator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import mvs.translator.TranslatorApp
import ru.fylmr.profdev.di.RepoModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepoModule::class,
        AndroidInjectionModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TranslatorApp)
}
