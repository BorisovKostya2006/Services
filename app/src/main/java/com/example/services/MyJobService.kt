package com.example.services

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.Provider

class MyJobService : JobService() {
    private val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d(TAG,"onDestroy")
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG,"onStartCommand")
        scope.launch {
            var workItem = params?.dequeueWork()
            while (workItem != null){
                val page = workItem.intent.getIntExtra(PUT_EXTRA_NAME,0)
                for (i in 0 until  5){
                    delay(1000)
                    Log.d(TAG,"Timer $i, $page")
                }
                params?.completeWork(workItem)
                workItem = params?.dequeueWork()
            }
            jobFinished(params,false)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG,"onStopJob")
        return false
    }
    companion object{
        private const val PUT_EXTRA_NAME = "intExtra"
        private const val TAG = "MyJobService"
        fun newIntent(page : Int) : Intent{
            return Intent().putExtra(PUT_EXTRA_NAME,page)
        }
    }
}