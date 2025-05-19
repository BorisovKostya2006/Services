package com.example.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.security.Provider

class MyServices : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("SERVICE","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SERVICE","onStartCommand")
        for (i in 0 until  1000){
            Thread.sleep(1000)
            Log.d("SERVICE","Timer $i")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SERVICE","onDestroy")
    }


    companion object{
        fun newIntent(context: Context) : Intent{
            return Intent(context, MyServices::class.java)
        }
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}