package com.example.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.Provider

class MyServices : Service() {
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand")
        scope.launch {
            for (i in 0 until  1000){
                delay(1000)
                Log.d(TAG,"Timer $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d(TAG,"onDestroy")
    }


    companion object{
        private const val TAG = "SERVICE"
        fun newIntent(context: Context) : Intent{
            return Intent(context, MyServices::class.java)
        }
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}