package com.dieunn.vlcforandroid.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dieunn.vlcforandroid.R
import com.dieunn.vlcforandroid.databinding.FragmentMusicBinding
import com.dieunn.vlcforandroid.presentation.adapters.MusicListAdapter
import com.dieunn.vlcforandroid.repository.model.Music
import com.dieunn.vlcforandroid.viewmodel.MusicViewModel


class MusicFragment : Fragment() {
    private lateinit var binding:FragmentMusicBinding
    private val viewModel by viewModels<MusicViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMusicBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun updateUi() {
        viewModel.getData(requireContext()).observe(viewLifecycleOwner) {
            setRecyclerView(it)
        }
    }

    private fun setRecyclerView(data:List<Music>) {
        binding.fragmentMusicList.apply {
            adapter = MusicListAdapter(requireContext(), data)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}