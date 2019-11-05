package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.detail

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emmanuel.martinez.rodriguez.androiddevelopertest.R
import com.emmanuel.martinez.rodriguez.androiddevelopertest.databinding.ActivityDetailBinding
import com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.detail.adapters.ComicAdapter
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult
import com.emmanuel.martinez.rodriguez.core.models.comic_models.ComicDataWrapper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*

class Detail : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    private lateinit var viewmodel:DetailViewModel
    private lateinit var character:CharacterResult
    private lateinit var adapter:ComicAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        viewmodel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        character = Gson().fromJson(intent.getStringExtra("character"),CharacterResult::class.java)
        title = character.name
        val url = "${character.thumbnail?.path}/standard_fantastic.${character.thumbnail?.extension}"
        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.img)

        if( character.description != null && character.description!!.isNotEmpty()){
            binding.description.text = character.description
        }

        viewmodel.comics(character.id).observe(this,Observer<ComicDataWrapper>{result->

            adapter = ComicAdapter(Glide.with(this),result.data?.results)
            recyclerView.adapter = adapter

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
