@file:Suppress("DEPRECATION")

package com.example.serviceexam2

import android.os.AsyncTask

class Socket<Params, Progress, Result> : AsyncTask<Params, Progress, Result>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }
    override fun doInBackground(vararg params: Params): Result {
        TODO("Not yet implemented")

        publishProgress(1)
    }

    override fun onProgressUpdate(vararg values: Progress) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Result) {
        super.onPostExecute(result)
    }
}