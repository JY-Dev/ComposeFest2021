package com.jydev.networktest.remote.service

import com.jydev.networktest.remote.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("client_id") clientId: String = "AnWwo5CtObz5iIy_GWI0Ge50YdEekidIx9x6riVowDI"
    ): List<PhotosResponse>
}