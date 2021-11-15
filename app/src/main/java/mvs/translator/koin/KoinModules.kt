package mvs.translator.koin

import androidx.room.Room
import mvs.translator.data.local.RepositoryImplementationLocal
import mvs.translator.data.local.RepositoryLocal
import mvs.translator.data.local.RoomDataBaseImplementation
import mvs.translator.data.remote.Repository
import mvs.translator.data.remote.RepositoryImplementation
import mvs.translator.data.remote.RetrofitImplementation
import mvs.translator.model.DataModel
import mvs.translator.model.room.HistoryDataBase
import mvs.translator.view.history.HistoryActivity
import mvs.translator.view.history.HistoryInteractor
import mvs.translator.view.history.HistoryViewModel
import mvs.translator.view.main.MainActivity
import mvs.translator.view.main.MainInteractor
import mvs.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}
