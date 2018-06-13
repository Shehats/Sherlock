package com.sherlock.watson.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ApiUtils (
        private val context: Context
){
    var requestQueue: RequestQueue?
    var count = 0
    init {
        this.requestQueue = Volley.newRequestQueue(context)
    }
    fun <T> addToQueue (request: Request<T>, tag: String?=null) {
        if (tag != null)
            request.tag = tag
        requestQueue?.add(request)
    }

}