package com.dieunn.vlcforandroid.presentation

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dieunn.vlcforandroid.R
import com.dieunn.vlcforandroid.databinding.ActivityMainBinding
import com.dieunn.vlcforandroid.viewmodel.VideoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureStatusBar()
        setUpBottomNavigation()

    }

    private fun configureStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
    }

    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as NavHostFragment
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        val mNavController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(mNavController)


    }
}