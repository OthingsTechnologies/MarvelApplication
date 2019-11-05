package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class App :Application() {

    override fun onCreate() {
        super.onCreate()



    }

    fun hasNetwork():Boolean?{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork:NetworkInfo? = connectivityManager.activeNetworkInfo

        if( activeNetwork != null && activeNetwork.isConnected ){
            return true
        }

        return false

    }



}