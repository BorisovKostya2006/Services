package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var page = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonServices.setOnClickListener {
            startService(MyServices.newIntent(this))
        }


        binding.buttonJobService.setOnClickListener {
            ContextCompat.startForegroundService(this, MyForegroundService.newIntent(this))
        }
        binding.buttonIntentService.setOnClickListener {
            ContextCompat.startForegroundService(this, MyIntentService.newIntent(this))
        }
        binding.buttonJobService.setOnClickListener {
            val componentName = ComponentName(this, MyJobService::class.java)

            val jobInfo = JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .build()
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val intent = MyJobService.newIntent(page++)
            jobScheduler.enqueue(jobInfo, JobWorkItem(intent))

        }

    }


}

