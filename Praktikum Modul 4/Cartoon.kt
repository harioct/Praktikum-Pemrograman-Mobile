package com.example.cartoons

import com.squareup.moshi.Json
import java.io.Serializable

data class Cartoon(
    val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "year") val year: Int,
    @Json(name = "creator") val creator: List<String>,
    @Json(name = "image") val image: String?,
    @Json(name = "genre") val genre: List<String>,
    @Json(name = "episodes") val episodes: Int
) : Serializable
