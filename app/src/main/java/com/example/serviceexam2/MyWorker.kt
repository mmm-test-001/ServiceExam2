package com.example.serviceexam2

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams){
    override fun doWork(): Result {
//        TODO("Not yet implemented")

        Log.i("MyWorker", "doWork")

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}