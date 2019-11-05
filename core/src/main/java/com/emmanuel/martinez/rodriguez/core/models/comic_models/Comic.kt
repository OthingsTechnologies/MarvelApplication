package com.emmanuel.martinez.rodriguez.core.models.comic_models

import com.google.gson.annotations.SerializedName

data class Comic(@SerializedName("creators")
                 val creators: Creators,
                 @SerializedName("issueNumber")
                 val issueNumber: Int = 0,
                 @SerializedName("isbn")
                 val isbn: String = "",
                 @SerializedName("description")
                 val description: String = "",
                 @SerializedName("title")
                 val title: String = "",
                 @SerializedName("diamondCode")
                 val diamondCode: String = "",
                 @SerializedName("characters")
                 val characters: Characters,
                 @SerializedName("urls")
                 val urls: List<UrlsItem>?,
                 @SerializedName("ean")
                 val ean: String = "",
                 @SerializedName("modified")
                 val modified: String = "",
                 @SerializedName("id")
                 val id: Double = 0.0,
                 @SerializedName("prices")
                 val prices: List<PricesItem>?,
                 @SerializedName("events")
                 val events: Events,
                 @SerializedName("pageCount")
                 val pageCount: Int = 0,
                 @SerializedName("thumbnail")
                 val thumbnail: Thumbnail,
                 @SerializedName("images")
                 val images: List<ImagesItem>?,
                 @SerializedName("stories")
                 val stories: Stories,
                 @SerializedName("textObjects")
                 val textObjects: List<TextObjectsItem>?,
                 @SerializedName("digitalId")
                 val digitalId: Int = 0,
                 @SerializedName("format")
                 val format: String = "",
                 @SerializedName("upc")
                 val upc: String = "",
                 @SerializedName("dates")
                 val dates: List<DatesItem>?,
                 @SerializedName("resourceURI")
                 val resourceURI: String = "",
                 @SerializedName("variantDescription")
                 val variantDescription: String = "",
                 @SerializedName("issn")
                 val issn: String = "",
                 @SerializedName("series")
                 val series: Series)