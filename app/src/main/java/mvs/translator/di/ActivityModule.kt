package mvs.translator.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mvs.translator.ui.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
