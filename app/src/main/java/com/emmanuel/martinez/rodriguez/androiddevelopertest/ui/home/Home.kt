package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.emmanuel.martinez.rodriguez.androiddevelopertest.R
import com.emmanuel.martinez.rodriguez.androiddevelopertest.databinding.ActivityHomeBinding
import com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.detail.Detail
import com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home.adapters.CharacterPagedListAdapter
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class Home : AppCompatActivity() {

    private var doubleBackToExit = false
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewmodel: HomeViewModel
    private lateinit var adapter: CharacterPagedListAdapter
    private lateinit var characters: MutableList<CharacterResult>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
            val factory = HomeViewModelFactory(10)
            viewmodel =   ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)

        } else {

            val factory = HomeViewModelFactory(20)
            viewmodel =   ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)

            val orientation = resources.configuration.orientation

            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
            } else{
                binding.recyclerView.layoutManager = GridLayoutManager(this, 5)
            }

        }


        characters = ArrayList()

        adapter = CharacterPagedListAdapter(Glide.with(this))
        binding.recyclerView.adapter = adapter

        adapter.onClick.observe(this,Observer<CharacterPagedListAdapter.Item>{item->

            val intent = Intent(this,Detail::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,item.img,"img")
            intent.putExtra("character",Gson().toJson(item.character))
            startActivity(intent,options.toBundle())

        })


        viewmodel.pagedListLiveData.observe(this, Observer<PagedList<CharacterResult>> { results ->

            adapter.submitList(results)
            adapter.notifyDataSetChanged()

        })


        viewmodel.loading.observe(this,Observer<Boolean>{result->
            if( result ){
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.GONE
            }

        })

        viewmodel.errors.observe(this,Observer<Throwable>{error->

            Snackbar.make(binding.root,error.message.toString(),Snackbar.LENGTH_SHORT).show()
            binding.retryButton.visibility = View.VISIBLE

        })

        binding.retryButton.setOnClickListener {
            viewmodel.characterDataSourceFactory.dataSource.retry()
            binding.retryButton.visibility = View.GONE


        }

    }

    override fun onBackPressed() {

        if (doubleBackToExit) {
            super.onBackPressed()
            finishAffinity()
        }

        doubleBackToExit = true
        Toast.makeText(this, "tap back again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExit = false }, 2000)

    }
}
