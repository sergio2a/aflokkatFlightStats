package com.example.flightaflokkat

import android.os.AsyncTask
import android.util.Log

/**
 * Created by sergio on 3/4/21
 * All rights reserved GoodBarber
 */
class FlightListAsyncTask(listener: RequestsManager.RequestListener) : AsyncTask<String, String, String>() {
    val mListener = listener


    override fun doInBackground(vararg urls: String?): String {

        val url =urls[0]

        val result = RequestsManager.get(url, HashMap())
        Log.i("RESULT", result!!)


        //Activity.onRequest Failed ou Success
        return result
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(result != null){
            mListener.onRequestSuccess(result)
        }
        else{
            mListener.onRequestFailed()
        }
    }

}