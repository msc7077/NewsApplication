package com.msc.application.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class AlternativeSlugs(
    val ko: String
)

data class Urls(
    val regular: String
)

data class UnsplashPhoto(
    val id: String,
    val slug: String,
    val alternativeSlugs: AlternativeSlugs?,
    val urls: Urls
)

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashPhoto>
}