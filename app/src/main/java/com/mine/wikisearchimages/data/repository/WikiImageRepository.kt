package com.example.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.rickandmorty.data.remote.WikiImageRemoteDataSource
import com.mine.wikisearchimages.data.entities.Images
import com.mine.wikisearchimages.data.local.WikiImagesDao
import com.mine.wikisearchimages.utils.Resource
import com.mine.wikisearchimages.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

class WikiImageRepository @Inject constructor(
    private val remoteDataSource: WikiImageRemoteDataSource,
    private val localDataSource: WikiImagesDao
) {

    fun getImages(term: String) = performGetOperation(
        databaseQuery = {
            localDataSource.getAllImages(term) },
        networkCall = {
            remoteDataSource.getImages(term) },
        saveCallResult = {
            val searchedImages = mutableListOf<Images>()
            it.query.pages.values.filter {
                it.thumbnail != null
            }.map {
                searchedImages.add(Images(it.pageid, it.thumbnail!!.source, term))
            }
            localDataSource.insertAll(searchedImages)
        }
    )
}