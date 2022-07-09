package com.dieunn.vlcforandroid.repository.model

import android.graphics.Bitmap
import android.net.Uri
import java.time.Duration

data class Music(
    val name: String = "",
    val id:Long = 0,
    val artist:String? = null,
    val path: Uri? = null,
    val thumbNail: Bitmap? = null,
    val duration: Int = 0,
)
