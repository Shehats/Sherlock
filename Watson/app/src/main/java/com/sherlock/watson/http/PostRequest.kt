package com.sherlock.watson.http

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import org.json.JSONObject

class PostRequest<T> (
        url: String,
        data: T,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
): JsonObjectRequest(Method.POST, url, JSONObject(Gson().toJson(data)),listener, errorListener)

class PutRequest<T> (
        url: String,
        data: T,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
): JsonObjectRequest(Method.PUT, url, JSONObject(Gson().toJson(data)),listener, errorListener)

class DeleteRequest<T>(
        url: String,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener,
        data: T? = null
): JsonObjectRequest(Method.DELETE, url, if (data != null) JSONObject(Gson().toJson(data)) else null, listener, errorListener)
