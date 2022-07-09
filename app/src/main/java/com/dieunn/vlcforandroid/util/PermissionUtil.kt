package com.dieunn.vlcforandroid.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil {
    companion object {
        fun isPermissionGranted(context: Context, permission: String): Boolean {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
            return true
        }

        fun requestPermission(activity: Activity, permission: Array<String>, requestCode: Int) {
            ActivityCompat.requestPermissions(activity, permission, requestCode)
        }
    }
}