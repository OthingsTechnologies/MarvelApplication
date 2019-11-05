package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class DatesItem(@SerializedName("date")
                     val date: String = "",
                     @SerializedName("type")
                     val type: String = "")