package com.jydev.networktest.remote.model

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String?,
    val urls: UrlsResponse
){
    data class UrlsResponse(
        val raw: String?,
        val full: String?,
        val regular: String?,
        val small: String?,
        val thumb: String?
    )
}