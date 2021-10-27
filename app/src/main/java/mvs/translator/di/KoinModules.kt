package mvs.translator.di

import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.RetrofitImplementation
import mvs.translator.model.datasource.RoomDataBaseImplementation
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
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
