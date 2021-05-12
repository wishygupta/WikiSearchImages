package com.example.rickandmorty.data.remote

import com.mine.wikisearchimages.data.entities.APIResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WikiImagesService {

    @GET("api.php?action=query&prop=pageimages&format=json&piprop=thumbnail&pithumbsize=200&pilimit=50&generator=prefixsearch")
    suspend fun searchImages(@Query("gpssearch") searchTerm: String): Response<APIResult>
}