package com.example.services

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(context: Context, private val workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    override fun doWork(): Result {
        val page = workerParameters.inputData.getInt(KEY_DATA,0)
        Log.d(TAG,"doWork")
            for (i in 0 until  1000){
                Thread.sleep(1000)
                Log.d(TAG,"Timer $i $page")
            }
        return Result.success()
    }




    companion object{
        const val MY_NAME = "myWork"
        const val PAGE = "page"
        private const val KEY_DATA = "PAGE"
        private const val TAG = "MyWorker"
        fun makeRequest(page : Int) : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().apply {
                setInputData(workDataOf(PAGE to page))
                setConstraints(makeConstraints())
            }.build()

        }
        fun makeConstraints() : Constraints{
            return Constraints.Builder().setRequiresCharging(true).build()
        }
    }
}