package com.zawmyat.rickandmortyuniverse.models

import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response:okhttp3.Response = chain.proceed(chain.request())
        val cacheControl = okhttp3.CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}