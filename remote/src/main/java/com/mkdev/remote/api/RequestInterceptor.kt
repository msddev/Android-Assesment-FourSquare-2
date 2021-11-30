package com.mkdev.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor : Interceptor {

    private val foursquareClientId = "25GXIKX302M31DLFOLW1SITLWLCBXJ3EIIHC2HEFWO50HVIC"
    private val foursquareClientSecret = "WIAGCMJRDDHHOXGQXFHIAB4W3NUUJSYS12BD5GO2OA4YS1L1"
    private val foursquareClientVersion = "20181123"

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("client_id", foursquareClientId)
            .addQueryParameter("client_secret", foursquareClientSecret)
            .addQueryParameter("v", foursquareClientVersion)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}