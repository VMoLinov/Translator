package mvs.translator.interactor.main

import io.reactivex.Observable
import mvs.translator.AppState
import mvs.translator.DataModel
import mvs.translator.Interactor
import mvs.translator.Repository
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, isRemoteSource: Boolean): Observable<AppState> {
        return if (isRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
