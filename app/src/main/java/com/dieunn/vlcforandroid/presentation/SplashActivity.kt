package com.dieunn.vlcforandroid.presentation

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import com.dieunn.vlcforandroid.R
import com.dieunn.vlcforandroid.repository.AppRepository

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureScreen()
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
            }, 2000
        )
    }

    private fun configureScreen() {
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        actionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.statusBarColor = Color.TRANSPARENT

    }
}