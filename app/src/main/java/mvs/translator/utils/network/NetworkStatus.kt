package mvs.translator.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService

class NetworkStatus(context: Context) : INetworkStatus {

    private var status: Boolean

    init {
        status = false
        val connectivityManager = context.getSystemService<ConnectivityManager>()
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(
            request, object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    status = true
                }

                override fun onUnavailable() {
                    status = false
                }

                override fun onLost(network: Network) {
                    status = false
                }
            }
        )
    }

    override fun isOnline(): Boolean = status
}
