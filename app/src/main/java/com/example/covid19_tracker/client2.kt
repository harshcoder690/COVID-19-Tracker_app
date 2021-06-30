package com.example.covid19_tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object client2 {
    private val okHttpClient1 = OkHttpClient()


    private val request1 = Request.Builder()
            .url("https://api.covid19india.org/data.json")
            .build()

    val api= okHttpClient1.newCall(request1)
}