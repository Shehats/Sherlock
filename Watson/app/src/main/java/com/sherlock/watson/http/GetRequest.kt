package com.sherlock.watson.http

import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class GetRequest<T>(
        url: String,
        private val C: Class<T>,
        private val listener: Response.Listener<T>,
        errorListener: Response.ErrorListener,
        private val header: MutableMap<String, String>? = null
): Request<T> (Method.GET, url, errorListener) {
    private val gson = Gson()

//    override fun getHeaders(): MutableMap<String, String> = headers?: super.getHeaders()

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            val json = String(
                    response?.data ?: ByteArray(0),
                    Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            Response.success(gson.fromJson(json, this.C), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }
    override fun deliverResponse(response: T) = listener.onResponse(response)
}

class GetAllRequest<T> (
        url: String,
        public val C: Class<T>,
        private val listener: Response.Listener<List<T>>,
        errorListener: Response.ErrorListener,
        private val header: MutableMap<String, String>? = null
): Request<List<T>> (Method.GET, url, errorListener) {
    inner class ListTemplate (val data: List<Any>)
    private val gson = Gson()

//    override fun getHeaders(): MutableMap<String, String> = headers?: super.getHeaders()

    override fun parseNetworkResponse(response: NetworkResponse?): Response<List<T>> {
        return try {
            val json = String(
                    response?.data ?: ByteArray(0),
                    Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            Response.success(gson.toObject(json), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: JsonSyntaxException) {
            Response.error(ParseError(e))
        }
    }
    override fun deliverResponse(response: List<T>) = listener.onResponse(response)

    inline fun  Gson.toObject(json: String): List<T> {
        return this.fromJson<List<Any>>(json, object: TypeToken<List<Any>>() {}.type).map { this.fromJson(this.toJson(it), C)}
    }
}
