package com.dieunn.vlcforandroid.repository.model

import android.graphics.Bitmap
import android.net.Uri

data class Video(
    val id: Long = 0,
    val title: String = "",
    val imageUri: String = "",
    var path: Uri? = null,
    val duration: Int = 0,
    val size :Int = 0,
    val thumbnail:Bitmap? = null
)
