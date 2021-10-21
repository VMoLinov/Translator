package mvs.translator.data.remote

import io.reactivex.Observable
import mvs.translator.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") word: String): Observable<List<DataModel>>
}
