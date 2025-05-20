package com.example.services

import android.app.IntentService
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

class MyIntentService : IntentService("IntentService") {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d("MyIntentService","onCreate")
        createNotificationChannel()
        startForeground(1,createNotification())

    }
    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService","onHandleIntent")
        for (i in 0 until  1000){
            Thread.sleep(1000)
            Log.d("MyIntentService","Timer $i")
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService","onDestroy")
    }




    companion object{
        fun newIntent(context: Context) : Intent{
            return Intent(context, MyIntentService::class.java)
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





}