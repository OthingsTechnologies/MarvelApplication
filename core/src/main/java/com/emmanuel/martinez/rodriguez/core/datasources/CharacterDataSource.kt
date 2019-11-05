package com.emmanuel.martinez.rodriguez.core.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.emmanuel.martinez.rodriguez.core.API
import com.emmanuel.martinez.rodriguez.core.models.Service
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterDataWrapper
import com.emmanuel.martinez.rodriguez.core.utils.Constants
import com.emmanuel.martinez.rodriguez.core.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CharacterDataSource(private val pageSize: Int) : PageKeyedDataSource<Int, CharacterResult>() {


    var loading = MutableLiveData<Boolean>()
    var errors = MutableLiveData<Throwable>()

    private val retryExecutor: Executor = Executors.newSingleThreadExecutor()

    fun retry(){

        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute{
                it.invoke()
            }
        }

    }

    var retry: ( ()->Any )?= null
    private var service: Service? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CharacterResult>) {

        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val hash = Utils.md5("$timestamp${Constants.API_KEY_PRIVATE}${Constants.API_KEY_PUBLIC}")

        loading.postValue(true)
        service = API.apiService?.create(Service::class.java)
        service?.getCharacters(Constants.API_KEY_PUBLIC, hash, timestamp, pageSize, 1)
            ?.enqueue(object : Callback<CharacterDataWrapper> {

                override fun onResponse(
                    call: Call<CharacterDataWrapper>,
                    response: Response<CharacterDataWrapper>
                ) {

                    loading.postValue(false)
                    if (response.isSuccessful) {

                        val characters = response.body()

                        callback.onResult(characters?.data?.results!!, null, 11)

                    } else {
                        errors.postValue(Throwable("Something went wrong"))
                        retry = {
                            loadInitial(params,callback)
                        }
                    }
                }

                override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                    loading.postValue(false)
                    errors.postValue(Throwable("Something went wrong"))

                    retry = {
                        loadInitial(params,callback)
                    }

                }

            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterResult>) {

        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val hash = Utils.md5("$timestamp${Constants.API_KEY_PRIVATE}${Constants.API_KEY_PUBLIC}")
        loading.postValue(true)
        service = API.apiService?.create(Service::class.java)
        service?.getCharacters(Constants.API_KEY_PUBLIC, hash, timestamp, pageSize, params.key)
            ?.enqueue(object : Callback<CharacterDataWrapper> {

                override fun onResponse(
                    call: Call<CharacterDataWrapper>,
                    response: Response<CharacterDataWrapper>
                ) {

                    loading.postValue(false)
                    if (response.isSuccessful) {

                        val characters = response.body()

                        callback.onResult(characters?.data?.results!!, params.key + pageSize)

                    } else {
                        retry = {
                            loadAfter(params,callback)
                        }
                        errors.postValue(Throwable("Something went wrong"))

                    }

                }

                override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                    loading.postValue(false)
                    errors.postValue(Throwable("Something went wrong"))
                    retry = {
                        loadAfter(params,callback)
                    }
                }

            })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterResult>) {


    }

}