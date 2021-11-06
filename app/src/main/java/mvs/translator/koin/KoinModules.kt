package mvs.translator.koin

import androidx.room.Room
import mvs.translator.data.DataModel
import mvs.translator.data.remote.RetrofitImplementation
import mvs.translator.data.remote.Repository
import mvs.translator.data.remote.RepositoryImplementation
import mvs.translator.data.local.RepositoryLocal
import mvs.translator.view.history.HistoryInteractor
import mvs.translator.view.history.HistoryViewModel
import mvs.translator.view.main.MainInteractor
import mvs.translator.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), mvs.translator.data.local.HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<mvs.translator.data.local.HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        mvs.translator.data.local.RepositoryImplementationLocal(
            mvs.translator.data.local.RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
