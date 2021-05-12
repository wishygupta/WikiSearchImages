package com.example.rickandmorty.data.remote

import javax.inject.Inject

class WikiImageRemoteDataSource @Inject constructor(
    private val wikiImagesService: WikiImagesService
) : BaseDataSource() {

    suspend fun getImages(searchTerm: String) = getResult { wikiImagesService.searchImages(searchTerm) }
}