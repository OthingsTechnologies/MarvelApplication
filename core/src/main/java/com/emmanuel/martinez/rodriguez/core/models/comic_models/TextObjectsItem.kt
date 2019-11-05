package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class TextObjectsItem(@SerializedName("language")
                           val language: String = "",
                           @SerializedName("text")
                           val text: String = "",
                           @SerializedName("type")
                           val type: String = "")