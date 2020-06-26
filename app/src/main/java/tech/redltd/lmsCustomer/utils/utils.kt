package tech.redltd.lmsCustomer.utils

import android.app.AlertDialog
import android.content.Context
import dmax.dialog.SpotsDialog

fun Context.loadingDialog():AlertDialog{
    return SpotsDialog.Builder()
        .setMessage("Loading....")
        .setContext(this).build()
}