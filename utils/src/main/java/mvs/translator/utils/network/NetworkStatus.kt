package mvs.translator.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

open class NetworkStatus(context: Context) : OnlineRepository {

    override val availableNetworks: MutableLiveData<Boolean> = MutableLiveData(false)
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val request: NetworkRequest = NetworkRequest.Builder().build()
    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            availableNetworks.postValue(true)
        }

        override fun onUnavailable() {
            availableNetworks.postValue(false)
        }

        override fun onLost(network: Network) {
            availableNetworks.postValue(false)
        }
    }

    init {
        connectivityManager.registerNetworkCallback(request, callback)
    }
}
