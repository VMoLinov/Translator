package mvs.translator.koin

import mvs.translator.model.data.DataModel
import mvs.translator.model.data.db.CacheDataModel
import mvs.translator.model.data.db.DatabaseModel
import mvs.translator.model.data.db.RoomCache
import mvs.translator.model.datasource.DataSource
import mvs.translator.model.datasource.RetrofitImplementation
import mvs.translator.model.datasource.RoomImplementation
import mvs.translator.model.repository.Repository
import mvs.translator.model.repository.RepositoryImplementation
import mvs.translator.ui.main.MainInteractor
import mvs.translator.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<DataSource<List<DataModel>>>(named(NAME_LOCAL)) {
        RoomImplementation(get(named(NAME_CACHE)))
    }
    single<CacheDataModel>(named(NAME_CACHE)) { RoomCache(DatabaseModel.getInstance()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
