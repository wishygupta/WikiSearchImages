package com.mine.wikisearchimages.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Images(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val searchTerm: String
)