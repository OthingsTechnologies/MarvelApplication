package com.emmanuel.martinez.rodriguez.core.utils

object Constants {

    val BASE_URL = "https://gateway.marvel.com/v1/public/"
    val API_KEY_PUBLIC = "3b6240e645177930553d111a0cf0db97"
    val API_KEY_PRIVATE = "3563f3357f4b983583fcd0f6d4582a893f93717c"
    val CONNECTION_TIMEOUT: Long = 10 // 10 seconds
    val READ_TIMEOUT: Long = 2 // 2 seconds
    val WRITE_TIMEOUT: Long = 2 // 2 seconds
    val RECIPE_REFRESH_TIME = (60 * 60 * 24 * 30).toLong() // 30 days (in seconds)

}
