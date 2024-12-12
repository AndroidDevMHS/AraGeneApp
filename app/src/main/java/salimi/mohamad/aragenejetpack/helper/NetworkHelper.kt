package salimi.mohamad.aragenejetpack.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


val Context.currentConnectivityStatus: ConnectionsStatus
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityStatus(connectivityManager)
    }

private fun getCurrentConnectivityStatus(connectivityManager: ConnectivityManager): ConnectionsStatus {
    val connected = connectivityManager.allNetworks.any { network ->
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
    return if (connected) ConnectionsStatus.Available else ConnectionsStatus.UnAvailable
}

fun Context.observeConnectivityAsFlow() = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = createNetworkCallback { connectionState -> trySend(connectionState) }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    val currentState = getCurrentConnectivityStatus(connectivityManager)
    trySend(currentState)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

fun createNetworkCallback(callback: (ConnectionsStatus) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(ConnectionsStatus.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionsStatus.UnAvailable)
        }
    }
}

