package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        lateinit var binding: ActivityMainBinding


        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonServices.setOnClickListener {
                startService(MyServices.newIntent(this))
        }


        binding.buttonShowNotify.setOnClickListener {
                showNotify()
        }
    }
        @RequiresApi(Build.VERSION_CODES.O)
        private fun showNotify(){
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                val notificationChannel = NotificationChannel("channel_id",
                        "channel_name",
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(notificationChannel)
                val notification = NotificationCompat.Builder(this, "channel_id")
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .build()
                notificationManager.notify(1,notification)
        }


}

