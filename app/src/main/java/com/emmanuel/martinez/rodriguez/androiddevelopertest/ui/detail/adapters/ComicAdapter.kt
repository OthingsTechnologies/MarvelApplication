package com.emmanuel.martinez.rodriguez.androiddevelopertest.ui.detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.emmanuel.martinez.rodriguez.androiddevelopertest.R
import com.emmanuel.martinez.rodriguez.core.models.comic_models.Comic
import kotlinx.android.synthetic.main.comic_item_layout.view.img
import kotlinx.android.synthetic.main.comic_item_layout.view.name

class ComicAdapter(var requestManager: RequestManager, var items: MutableList<Comic>?) : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_item_layout, parent, false)
        return ViewHolder(requestManager,view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val comic = items?.get(position)
        holder.bind(comic!!)

    }


    class ViewHolder(var requestManager: RequestManager , view: View) : RecyclerView.ViewHolder(view) {

        var img = itemView.img
        val name = itemView.name

        fun bind(comic: Comic) {

            name.text = comic.title
            val url = "${comic.thumbnail.path}/portrait_uncanny.${comic.thumbnail.extension}"
            requestManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(img)

        }

    }

}