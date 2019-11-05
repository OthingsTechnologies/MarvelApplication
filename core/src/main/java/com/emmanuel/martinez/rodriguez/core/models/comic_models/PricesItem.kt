package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class PricesItem(@SerializedName("price")
                      val price: Double = 0.0,
                      @SerializedName("type")
                      val type: String = "")