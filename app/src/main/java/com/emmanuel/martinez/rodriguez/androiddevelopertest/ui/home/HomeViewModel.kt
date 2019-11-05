package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.emmanuel.martinez.rodriguez.core.datasources.CharacterDataSourceFactory
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult

class HomeViewModel(private val pageSize:Int):ViewModel() {

    private var pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(pageSize)
        .setPageSize(pageSize)
        .setPrefetchDistance(pageSize)
        .build()

    var pagedListLiveData:LiveData<PagedList<CharacterResult>>
    var characterDataSourceFactory: CharacterDataSourceFactory = CharacterDataSourceFactory(pageSize)


    init{

        pagedListLiveData = LivePagedListBuilder<Int,CharacterResult>(characterDataSourceFactory,pagedListConfig).build()

    }

    var errors = characterDataSourceFactory.dataSource.errors
    var loading = characterDataSourceFactory.dataSource.loading

}