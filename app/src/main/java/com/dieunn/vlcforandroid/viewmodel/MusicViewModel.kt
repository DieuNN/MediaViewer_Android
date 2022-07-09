package com.dieunn.vlcforandroid.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dieunn.vlcforandroid.repository.AppRepository
import com.dieunn.vlcforandroid.repository.model.Music

class MusicViewModel :ViewModel() {
    private val _music = MutableLiveData<List<Music>>()

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun loadData(context: Context) {
        _music.postValue(AppRepository.getRepoInstance().getMusic(context))
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getData(context: Context):MutableLiveData<List<Music>> {
        synchronized(this) {
            loadData(context = context)
            return _music
        }
    }
}