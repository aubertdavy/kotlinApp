package fr.daubert.myapp

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by daubert on 14/03/2018.
 */
class NetworkManager(private val context: Context) {

    private var status: Boolean? = false

    val isNetworkConnected: Boolean
        get() {
            val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return conManager.activeNetworkInfo != null && conManager.activeNetworkInfo.isConnected
        }
}