package com.dieunn.vlcforandroid.presentation.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dieunn.vlcforandroid.databinding.ItemVideoBinding
import com.dieunn.vlcforandroid.presentation.VideoPlayActivity
import com.dieunn.vlcforandroid.repository.model.Video
import com.dieunn.vlcforandroid.util.parseIntTimeToString

class VideoListAdapter(
    private val list: List<Video>,
    private val context: Context
) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.itemVideoTitle.text = this.title
                binding.itemVideoLength.text = parseIntTimeToString(this.duration)
                Glide.with(context)
                    .load(this.thumbnail)
                    .into(binding.itemVideoImg)
                binding.itemVideoLayout.setOnClickListener {
                    val intent = Intent(context, VideoPlayActivity::class.java)
                    intent.putExtra("uri", this.path)
                    (context as Activity).startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}