package com.emmanuel.martinez.rodriguez.core.models.characters_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDataWrapper(
    val code: Int = 0,
    val status: String? = null,
    val copyright: String? = null,
    val attributionText: String? = null,
    val attributionHTML: String? = null,
    val etag: String? = null,
    @Expose
    @SerializedName("data")
    val data: CharacterDataContainer? = null
)
