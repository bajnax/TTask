package com.example.ttask

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TestApi {
    @GET("users.json")
    fun getUsers() : Call<List<User>>

    companion object Factory {
        fun createWebService(): TestApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://a11d.firebaseio.com/")
                    .build()

            return retrofit.create(TestApi::class.java);
        }
    }
}