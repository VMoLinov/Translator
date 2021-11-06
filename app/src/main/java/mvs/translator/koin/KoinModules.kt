package mvs.translator.koin

import androidx.room.Room
import mvs.translator.model.data.DataModel
import mvs.translator.model.datasource.RetrofitImplementation
import mvs.translator.model.datasource.RoomDataBaseImplementation
import mvs.translator.model.repository.Repository
import mvs.translator.model.repository.RepositoryImplementation
import mvs.translator.model.repository.RepositoryImplementationLocal
import mvs.translator.model.repository.RepositoryLocal
import mvs.translator.room.HistoryDataBase
import mvs.translator.view.history.HistoryInteractor
import mvs.translator.view.history.HistoryViewModel
import mvs.translator.view.main.MainInteractor
import mvs.translator.view.main.MainViewModel
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
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
