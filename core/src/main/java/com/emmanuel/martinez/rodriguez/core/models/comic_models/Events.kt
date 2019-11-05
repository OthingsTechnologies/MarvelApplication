package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class Events(@SerializedName("collectionURI")
                  val collectionURI: String = "",
                  @SerializedName("available")
                  val available: Int = 0,
                  @SerializedName("returned")
                  val returned: Int = 0)