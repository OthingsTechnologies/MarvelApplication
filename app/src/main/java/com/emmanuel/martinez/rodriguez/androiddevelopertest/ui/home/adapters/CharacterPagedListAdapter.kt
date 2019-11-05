package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emmanuel.martinez.rodriguez.androiddevelopertest.R
import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterResult
import kotlinx.android.synthetic.main.character_item_layout.view.*

class CharacterPagedListAdapter(private var requestManager: RequestManager): PagedListAdapter<CharacterResult, RecyclerView.ViewHolder>(characterDiffCallback) {

    var onClick:MutableLiveData<Item> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item_layout,parent,false)
        return ViewHolder(onClick,requestManager,view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val h = holder as ViewHolder
        h.bind(getItem(position)!!)

    }

    class ViewHolder( var onClick:MutableLiveData<Item>, var requestManager: RequestManager ,view:View):RecyclerView.ViewHolder(view){

        fun bind( character:CharacterResult){

            itemView.name.text = character.name
            val url = "${character.thumbnail?.path}/standard_fantastic.${character.thumbnail?.extension}"
            requestManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(itemView.img)
            itemView.container.setOnClickListener {

                onClick.value = Item(character,itemView.img,itemView.name)

            }

        }

    }

    class Item( var character: CharacterResult, var img:ImageView , var name:TextView )

    companion object{


        val  characterDiffCallback = object:ItemCallback<CharacterResult>(){

            override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {

                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {

                return oldItem.equals( newItem )

            }


        }

    }



}