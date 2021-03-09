package com.example.flightaflokkat.flightlist

import android.os.AsyncTask
import android.util.Log
import com.example.flightaflokkat.utils.RequestsManager

/**
 * Created by sergio on 3/4/21
 * All rights reserved GoodBarber
 */
class FlightListAsyncTask(listener: RequestsManager.RequestListener) : AsyncTask<String, String, String>() {
    val mListener = listener


    override fun doInBackground(vararg urls: String?): String {

        val url =urls[0]

        var result = RequestsManager.get(
            url,
            HashMap()
        )
        if(result != null)
            Log.i("RESULT", result)
        else
            result = "Something wrong happened"


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