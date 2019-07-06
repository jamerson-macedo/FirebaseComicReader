package com.jmdevelopers.firebasecomicreader.adapter

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jmdevelopers.firebasecomicreader.R
import com.jmdevelopers.firebasecomicreader.model.Comic
import com.squareup.picasso.Picasso

class MyComicAdapter(internal var context: Context, internal var comicList: List<Comic>) :
    RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comic_item, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(comicList[position].Image).into(holder.imagemview)
        holder.textView.text = comicList[position].Name
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var imagemview: ImageView
        var textView: TextView

        init {
            imagemview=itemview.findViewById(R.id.comic_image) as ImageView
            textView= itemview.findViewById(R.id.comic_name) as TextView

        }

    }
}