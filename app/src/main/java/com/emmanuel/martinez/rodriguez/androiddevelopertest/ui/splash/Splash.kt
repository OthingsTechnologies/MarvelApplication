package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.emmanuel.martinez.rodriguez.androiddevelopertest.R
import com.emmanuel.martinez.rodriguez.androiddevelopertest.databinding.ActivitySplashBinding
import com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home.Home

class Splash : AppCompatActivity() {

    private var apiVersion: Int? = null
    private lateinit var binding:ActivitySplashBinding
    private lateinit var viewmodel:SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewmodel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        apiVersion = Build.VERSION.SDK_INT

        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)


        if (apiVersion!! >= Build.VERSION_CODES.KITKAT) {

            window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->

                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    window.decorView.systemUiVisibility = flags
                }

            }

        }


        Handler().postDelayed({

            val intent = Intent(this, Home::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,binding.logo,"marvel")

            startActivity(intent,options.toBundle())

        }, 3000)


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (apiVersion!! >= Build.VERSION_CODES.KITKAT && hasFocus) {

            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )

        }

    }

}
