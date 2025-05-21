package com.example.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyForegroundService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
        createNotificationChannel()
        startForeground(1,createNotification())

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand")
        scope.launch {
            for (i in 0 until  1000){
                delay(1000)
                Log.d(TAG,"Timer $i")
            }
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d(TAG,"onDestroy")
    }


    companion object{
        private const val TAG = "MyForegroundService"
        fun newIntent(context: Context) : Intent{
            return Intent(context, MyForegroundService::class.java)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel("channel_id",
            "channel_name",
            NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
    }
    private fun createNotification() : Notification{
        return NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

    }




    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}