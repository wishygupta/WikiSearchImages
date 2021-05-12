package com.mine.wikisearchimages.ui.images.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.repository.WikiImageRepository
import com.mine.wikisearchimages.data.entities.Images
import com.mine.wikisearchimages.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: WikiImageRepository
) : ViewModel() {

    fun fetchImages(search: String) = repository.getImages(search)

}