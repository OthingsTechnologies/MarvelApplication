package com.emmanuel.martinez.rodriguez.core.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult

class CharacterDataSourceFactory(private val pageSize: Int) : DataSource.Factory<Int, CharacterResult>() {


    var dataSource: CharacterDataSource = CharacterDataSource(pageSize)
    var mutableLiveData: MutableLiveData<CharacterDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, CharacterResult> {

        mutableLiveData.postValue(dataSource)
        return dataSource

    }


}