package com.dieunn.vlcforandroid.presentation.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dieunn.vlcforandroid.databinding.FragmentVideoBinding
import com.dieunn.vlcforandroid.presentation.adapters.VideoListAdapter
import com.dieunn.vlcforandroid.repository.model.Video
import com.dieunn.vlcforandroid.util.PermissionUtil
import com.dieunn.vlcforandroid.viewmodel.VideoViewModel

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private final val READ_EXTERNAL_STORAGE_REQUEST_CODE: Int = 101
    private val viewModel: VideoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        checkAndRequestPermission()
        updateUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
    }

    private fun updateUi() {
        viewModel.getVideos(requireContext()).observe(requireActivity()) {
            setupRecyclerViewAdapter(it)
        }
        refreshData()
    }

    private fun checkAndRequestPermission() {
        if (!PermissionUtil.isPermissionGranted(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            PermissionUtil.requestPermission(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    private fun setupRecyclerViewAdapter(data: List<Video>) {
        binding.fragmentVideoRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = VideoListAdapter(data, requireContext())
        }


    }

    private fun refreshData() {
        binding.fragmentVideoSwipeRefresh.setOnRefreshListener {
            viewModel.getVideos(requireContext()).observe(viewLifecycleOwner) {
                binding.fragmentVideoSwipeRefresh.isRefreshing = true
                setupRecyclerViewAdapter(it)
                binding.fragmentVideoSwipeRefresh.isRefreshing = false
            }
        }
    }


}