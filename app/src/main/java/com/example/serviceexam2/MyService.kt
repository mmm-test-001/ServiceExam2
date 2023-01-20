package com.example.serviceexam2

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    private val TAG = "com.example.serviceexam2"

    override fun onCreate() {
        Log.i(TAG, "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service onStartCommand")
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        val currentNetwork = connectivityManager.activeNetwork
        val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
        val linkProperties = connectivityManager.getLinkProperties(currentNetwork)

        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network : Network) {
                Log.e(TAG, "The default network is now: " + network)
            }

            override fun onLost(network : Network) {
                Log.e(TAG, "The application no longer has a default network. The last default network was " + network)
            }

            override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
                Log.e(TAG, "The default network changed capabilities: " + networkCapabilities)
            }

            override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
                Log.e(TAG, "The default network changed link properties: " + linkProperties)
            }
        })

        return Service.START_STICKY
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}

class MyJob : JobService() {
    var jobThread:Thread? = null
    override
    fun onStartJob(params: JobParameters) : Boolean {
        Log.i("LOG", "MyJob: onStartJob() : " +
                params.jobId)
        jobThread?.interrupt()
        jobThread = Thread {
            Log.i("LOG", "started job thread")
            // do job work...
            jobFinished(params, false)
            jobThread = null
            Log.i("LOG", "finished job thread")
        }
        jobThread!!.start()
        return true
    }
    override
    fun onStopJob(params:JobParameters) : Boolean {
        Log.i("LOG", "MyJob: onStopJob()")
        jobThread?.interrupt()
        jobThread = null
        return true
    }
}