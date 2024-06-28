package com.example.cartoons

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object RetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/cartoons/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: CartoonApi by lazy {
        retrofit.create(CartoonApi::class.java)
    }
}
