package com.emmanuel.martinez.rodriguez.core.models.characters_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDataContainer(val offset: Int = 0,
                                  val limit: Int = 0,
                                  val total: Int = 0,
                                  val count: Int = 0,
                                  @Expose
                                  @SerializedName("results")
                                  val results: MutableList<CharacterResult>? = null)
