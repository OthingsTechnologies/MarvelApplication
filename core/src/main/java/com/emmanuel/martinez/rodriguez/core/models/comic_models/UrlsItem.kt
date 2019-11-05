package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class UrlsItem(@SerializedName("type")
                    val type: String = "",
                    @SerializedName("url")
                    val url: String = "")