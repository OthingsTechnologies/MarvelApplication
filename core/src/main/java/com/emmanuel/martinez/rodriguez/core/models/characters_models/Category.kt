package com.emmanuel.martinez.rodriguez.core.models.characters_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category( val available: Int = 0,
                     val collectionURI: String? = null,
                     @Expose
                     @SerializedName("items")
                     val items: List<Item>? = null,
                     val returned: Int = 0)
