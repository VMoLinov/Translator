package mvs.translator.utils.network

import android.content.Context
import android.net.ConnectivityManager


fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netActive = connectivityManager.activeNetwork
    return netActive != null
}
