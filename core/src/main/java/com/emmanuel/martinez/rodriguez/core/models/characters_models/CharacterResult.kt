package com.emmanuel.martinez.rodriguez.core.models.characters_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterResult( val id: Int = 0,
                            val name: String? = null,
                            val description: String? = null,
                            val modified: String? = null,
                            @Expose
                            @SerializedName("thumbnail")
                            val thumbnail: CharacterThumbnail? = null,
                            val resourceURI: String? = null,
                            @Expose
                            @SerializedName("comics")
                            val comics: Category? = null,
                            @Expose
                            @SerializedName("series")
                            val series: Category? = null,
                            @Expose
                            @SerializedName("stories")
                            val stories: Category? = null,
                            @Expose
                            @SerializedName("events")
                            val events: Category? = null,
                            @Expose
                            @SerializedName("urls")
                            val urls: List<Url>? = null )
