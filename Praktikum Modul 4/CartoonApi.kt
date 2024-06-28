package com.example.cartoons

import retrofit2.http.GET

interface CartoonApi {
    @GET("cartoons2D")
    suspend fun getCartoons(): List<Cartoon>
}
