package com.dieunn.vlcforandroid.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dieunn.vlcforandroid.repository.AppRepository
import com.dieunn.vlcforandroid.repository.model.Video

class VideoViewModel : ViewModel() {
    private val _videos = MutableLiveData<List<Video>>()

    private fun loadVideos(context: Context) {
        _videos.postValue(AppRepository.getRepoInstance().getVideos(context = context))
    }

    fun getVideos(context: Context): MutableLiveData<List<Video>> {
        synchronized(this) {
            loadVideos(context)
            return _videos
        }
    }
}