package com.mine.wikisearchimages.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.wikisearchimages.data.entities.Images

@Dao
interface WikiImagesDao {

    @Query("SELECT * FROM images WHERE searchTerm = :term")
    fun getAllImages(term: String): LiveData<List<Images>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Images>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(images: Images)
}