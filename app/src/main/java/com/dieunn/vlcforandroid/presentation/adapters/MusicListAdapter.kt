package com.dieunn.vlcforandroid.presentation.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dieunn.vlcforandroid.R
import com.dieunn.vlcforandroid.databinding.ItemVideoBinding
import com.dieunn.vlcforandroid.databinding.MusicItemBinding
import com.dieunn.vlcforandroid.repository.model.Music
import com.dieunn.vlcforandroid.util.parseIntTimeToString

class MusicListAdapter(private val context: Context, private val list: List<Music>) :
    RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: MusicItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MusicItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.musicItemName.text = this.name
                binding.musicItemArtist.text = this.artist
                binding.musicItemDuration.text = parseIntTimeToString(this.duration)
                Glide.with(context)
                    .load(this.path)
                    .error(R.drawable.musical_note)
                    .into(binding.musicItemThumbnail)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}