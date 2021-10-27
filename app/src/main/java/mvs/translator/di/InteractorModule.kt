package mvs.translator.di

import dagger.Module
import dagger.Provides
import mvs.translator.DataModel
import mvs.translator.Repository
import mvs.translator.interactor.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) remoteRepo: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) localRepo: Repository<List<DataModel>>,
    ): MainInteractor {
        return MainInteractor(remoteRepo, localRepo)
    }
}
