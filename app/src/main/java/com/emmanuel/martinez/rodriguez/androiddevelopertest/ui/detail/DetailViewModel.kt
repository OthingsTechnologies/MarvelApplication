package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuel.martinez.rodriguez.core.models.comic_models.ComicDataWrapper
import com.emmanuel.martinez.rodriguez.core.repositories.MarvelRepository

class DetailViewModel:ViewModel() {

    private val repository:MarvelRepository = MarvelRepository()

    fun  comics( characterId:Int ):MutableLiveData<ComicDataWrapper>{

        return repository.getComics(characterId,1,20)

    }

}