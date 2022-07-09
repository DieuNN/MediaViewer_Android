package com.dieunn.vlcforandroid.repository

import android.app.Application
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import com.dieunn.vlcforandroid.R
import com.dieunn.vlcforandroid.repository.model.Music
import com.dieunn.vlcforandroid.repository.model.Video
import java.io.File
import kotlin.math.hypot

class AppRepository() {
    private final val TAG = "App Repository"

    companion object {
        private var instance: AppRepository? = null
        fun getRepoInstance(): AppRepository {
            if (instance == null) {
                instance = AppRepository()
            }
            return instance as AppRepository
        }
    }


    fun getVideos(context: Context): List<Video> {
        val result = mutableListOf<Video>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            null
        )
        query?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn =
                it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn =
                it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)


            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val duration = it.getInt(durationColumn)
                val size = it.getInt(sizeColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val thumbnail = MediaStore.Video.Thumbnails.getThumbnail(
                    context.contentResolver,
                    id,
                    MediaStore.Video.Thumbnails.MINI_KIND,
                    null
                )
                result.add(
                    Video(
                        id = id,
                        title = name,
                        duration = duration,
                        path = contentUri,
                        size = size,
                        thumbnail = thumbnail
                    )
                )
            }
        }
        query?.close()
        return result
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getMusic(context: Context): List<Music> {
        val result = mutableListOf<Music>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DISPLAY_NAME
        )
        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            null
        )

        Log.d(TAG, "getMusic: ${query?.columnCount} ")

        query?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DISPLAY_NAME)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val duration = it.getInt(durationColumn)
                val artist = it.getString(artistColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val thumbnail = try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ThumbnailUtils.createAudioThumbnail(File(contentUri.path!!), Size(48, 48), null)
                    } else {
                        // Use the deprecated version for older devices.
                        ThumbnailUtils.createAudioThumbnail(
                            contentUri.path!!,
                            MediaStore.Images.Thumbnails.MINI_KIND
                        )
                    }
                } catch (e :Exception) {
                    BitmapFactory.decodeResource(context.resources, R.drawable.musical_note)
                }

                val music = Music(
                    name = name,
                    id = id,
                    duration = duration,
                    artist = artist,
                    path = contentUri,
                    thumbNail = thumbnail
                )
                result.add(music)
            }
        }
        Log.d(TAG, "getMusic: $result")
        query?.close()
        return result
    }


}

