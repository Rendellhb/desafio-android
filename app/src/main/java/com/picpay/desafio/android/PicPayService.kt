package com.picpay.desafio.android

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.ConnectivityInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

private val gson: Gson by lazy { GsonBuilder().create() }

fun retrofit(context: Context): Retrofit {
    val okHttp = OkHttpClient.Builder()
        .addInterceptor(ConnectivityInterceptor(context))
        .build()

    return Retrofit.Builder()
        .baseUrl("http://careers.picpay.com/tests/mobdev/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun <T> callback(callResponse: (response: Response<T>?,
                                throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            callResponse(response, null)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            callResponse(null, t)
        }
    }
}

class NoConnectivityException: IOException()