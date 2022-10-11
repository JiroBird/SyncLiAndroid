package com.github.jirobird.syncli.screens.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.github.jirobird.syncli.R
import com.github.jirobird.syncli.screens.main.MainScreanActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//
        val androidImageView: ImageView = findViewById(R.id.iv_splash_icon)
        val imgDrawable = androidImageView.drawable
        if (imgDrawable is Animatable) {
            (imgDrawable as Animatable).start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainScreanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY and  Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }, 1000)
    }
}