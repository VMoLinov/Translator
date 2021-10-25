package ru.fylmr.profdev.di

import dagger.Module
import dagger.Provides
import mvs.translator.DataModel
import mvs.translator.DataSource
import mvs.translator.Repository
import mvs.translator.data.RepoImpl
import mvs.translator.data.remote.RetrofitImplementation
import mvs.translator.di.NAME_LOCAL
import mvs.translator.di.NAME_REMOTE
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRemoteRepo(@Named(NAME_REMOTE) dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideLocalRepo(@Named(NAME_LOCAL) dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation()
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideLocalDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation() // todo
    }
}
