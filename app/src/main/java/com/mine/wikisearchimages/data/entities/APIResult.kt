package com.mine.wikisearchimages.data.entities

import com.google.gson.annotations.SerializedName

data class APIResult(
    @SerializedName("query")
    val query: Query
)

data class Query(
    @SerializedName("pages")
    val pages: Map<String, Pages>
)

data class Pages(
    @SerializedName("pageid")
    val pageid: Int,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?
)

data class Thumbnail(
    @SerializedName("source")
    val source: String
)