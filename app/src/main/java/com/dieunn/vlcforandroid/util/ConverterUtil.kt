package com.dieunn.vlcforandroid.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
fun parseIntTimeToString(time: Int): String {
    return if (time > 3600000) {
        val hour = (time / 1000) / 3600
        val minute = (time / 1000) / 60
        val second = (time / 1000) % 60
        "$hour:$minute:$second"
    } else if (time < 1000) {
        "0:1"
    } else {
        val minute = (time / 1000) / 60
        val second = (time / 1000) % 60
        "$minute:$second"
    }
}