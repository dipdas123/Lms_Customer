package tech.redltd.lmsCustomer.utils

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class AppUtils(private val context: Context) {



    fun saveDataIntoPreference(key: String?,value: String?) {
        val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getDataFromPreference(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

     fun checkInternet(context: Context): Boolean {
        var isInternetOK = false
        try {
            val connectivityManager =
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected) {
                isInternetOK = true
            }
        } catch (ex: Exception) {
        }
        return isInternetOK
    }

    fun goSettingsActivity() {
        if (!checkInternet(context)) {
            AlertDialog.Builder(context)
                .setMessage("Sorry! Internet is not enable.")
                .setTitle("Internet Disable")
                .setCancelable(false)
                .setPositiveButton(
                    "Settings"
                ) { dialogInterface, i ->
                    val intent = Intent(Settings.ACTION_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    ContextCompat.startActivity(context, intent, null)
                }
                .create()
                .show()
        }
    }
}