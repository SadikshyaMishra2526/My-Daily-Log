package com.mydailyjournal.ui.activities.splashScreen

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mydailyjournal.databinding.ActivitySplashScreenBinding
import com.mydailyjournal.R

import android.content.Intent
import android.os.Handler
import com.mydailyjournal.ui.activities.home.HomeActivity


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var imageList: ArrayList<Int>

    private val SPLASH_TIME_OUT = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageList = ArrayList()
        imageList.add(R.drawable.dairy)
        imageList.add(R.drawable.dairy)
        imageList.add(R.drawable.dairy)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        switchToHome()
    }

    private fun switchToHome() {
        Handler().postDelayed(Runnable {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, (SPLASH_TIME_OUT * 2).toLong())
    }

}