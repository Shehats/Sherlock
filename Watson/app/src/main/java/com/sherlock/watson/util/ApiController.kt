package com.sherlock.watson.util

import com.android.volley.Response
import com.sherlock.watson.http.*
import org.json.JSONObject

class ApiController<T> (private var apiUtils: ApiUtils, private var clazz: Class<T>){
    fun post (url: String, data: T, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener) {
        this.apiUtils.addToQueue(PostRequest(url, data, listener, errorListener))
    }
    fun get (url: String, listener: Response.Listener<T>, errorListener: Response.ErrorListener) {
        this.apiUtils.addToQueue(GetRequest(url, this.clazz, listener, errorListener ))
    }
    fun getAll (url: String, listener: Response.Listener<List<T>>, errorListener: Response.ErrorListener) {
        this.apiUtils.addToQueue(GetAllRequest(url, this.clazz, listener, errorListener))
    }
    fun put (url: String, data: T, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener) {
        this.apiUtils.addToQueue(PutRequest(url, data, listener, errorListener))
    }
    fun delete (url: String, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener, data: T?=null) {
        this.apiUtils.addToQueue(DeleteRequest(url, listener, errorListener, data))
    }
}