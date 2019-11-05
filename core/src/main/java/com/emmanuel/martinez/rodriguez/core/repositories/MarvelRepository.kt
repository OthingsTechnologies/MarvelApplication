package com.emmanuel.martinez.rodriguez.core.repositories

import androidx.lifecycle.MutableLiveData
import com.emmanuel.martinez.rodriguez.core.API
import com.emmanuel.martinez.rodriguez.core.models.Service
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterDataWrapper
import com.emmanuel.martinez.rodriguez.core.models.comic_models.ComicDataWrapper
import com.emmanuel.martinez.rodriguez.core.utils.Constants
import com.emmanuel.martinez.rodriguez.core.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelRepository {

    private val service = API.apiService?.create(Service::class.java)
    private val characters:MutableLiveData<CharacterDataWrapper> = MutableLiveData()
    private val comics:MutableLiveData<ComicDataWrapper> = MutableLiveData()
    val errors:MutableLiveData<Throwable> = MutableLiveData()
    val timestamp = (System.currentTimeMillis()/1000).toString()
    val hash = Utils.md5("$timestamp${Constants.API_KEY_PRIVATE}${Constants.API_KEY_PUBLIC}")

    fun getCharacters( offset:Int , limit:Int ):MutableLiveData<CharacterDataWrapper>{

        service?.getCharacters(Constants.API_KEY_PUBLIC,hash,timestamp,limit,offset)?.enqueue(object:Callback<CharacterDataWrapper>{

            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {

                if( response.isSuccessful ){
                    characters.value = response.body()
                }
                else{
                    errors.value = Throwable("ERROR ${response.code()}")
                }

            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                errors.value = t
            }

        })

        return characters

    }

    fun getComics( characterId:Int, offset: Int , limit: Int ):MutableLiveData<ComicDataWrapper>{

      service?.getComics(characterId,Constants.API_KEY_PUBLIC,hash,timestamp,limit,offset)?.enqueue(object:Callback<ComicDataWrapper>{

          override fun onResponse(call: Call<ComicDataWrapper>, response: Response<ComicDataWrapper>) {

              if( response.isSuccessful ){
                  comics.value = response.body()
              }
              else{
                  errors.value = Throwable("ERROR ${response.code()}")
              }

          }

          override fun onFailure(call: Call<ComicDataWrapper>, t: Throwable) {

              errors.value = t

          }

      })

        return comics
    }
}