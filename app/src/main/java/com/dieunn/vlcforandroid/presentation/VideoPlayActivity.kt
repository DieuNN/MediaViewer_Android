package com.dieunn.vlcforandroid.presentation

import android.app.PictureInPictureParams
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.dieunn.vlcforandroid.databinding.ActivityVideoPlayBinding
import com.dieunn.vlcforandroid.viewmodel.VideoViewModel

class VideoPlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayBinding
    private lateinit var player: ExoPlayer
    private lateinit var viewModel: VideoViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
        setContentView(binding.root)
        initializePlayer()
        setPIPPlay()
    }

    private fun getIntentExtra(): Uri? {
        return intent.extras?.getParcelable("uri")
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build().also { it ->
            binding.exoPlayer.player = it
            val mediaItem = MediaItem.fromUri(getIntentExtra()!!)
            it.addMediaItem(mediaItem)
            it.playWhenReady = true
            it.prepare()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setPIPPlay() {
        if (binding.exoPlayer.isVisible) {
            binding.exoPlayerPip.setOnClickListener {
                val params = PictureInPictureParams.Builder().build()
                enterPictureInPictureMode(params)
            }
        }
    }


}