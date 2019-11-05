package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class ImagesItem(@SerializedName("path")
                      val path: String = "",
                      @SerializedName("extension")
                      val extension: String = "")