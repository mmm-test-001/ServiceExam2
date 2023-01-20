package com.example.serviceexam2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClock(view: View){
        intent = Intent(this,MyService::class.java)
        startService(intent)
    }

    fun onTest(view: View){
        Log.i("Test","onTest")

        val myWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<MyWorker>()
                .build()

        WorkManager
            .getInstance(view.context)
            .enqueue(myWorkRequest)
    }

    fun onSocket(view:View){
        Log.i("Test","onSocket")
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            // 发生异常时的捕获
        }
        GlobalScope.launch(errorHandler){
            //do it here
            Log.i("Test","GlobalScope.launch")

            var i = 0
            while(i < 3){
                doOnUiCode()
                i++
            }
        }
    }

    private suspend fun doOnUiCode() {
        withContext(Dispatchers.Main) {
            // 更新你的UI
            Log.i("Test","doOnUiCode")
        }
    }
}