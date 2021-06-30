package com.example.covid19_tracker

import okhttp3.OkHttpClient
import okhttp3.Request

//singleton class
//we doesn't need to make a object of this class
//In kotlin we have to use object keyword to make singleton class
object Client {
    private val okHttpClient =OkHttpClient()


    private val request = Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()

    val api= okHttpClient.newCall(request)

}