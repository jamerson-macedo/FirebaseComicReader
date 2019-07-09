package com.jmdevelopers.firebasecomicreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.jmdevelopers.firebasecomicreader.IRecyclerItemClick
import com.jmdevelopers.firebasecomicreader.R
import com.jmdevelopers.firebasecomicreader.model.Chapter
import kotlinx.android.synthetic.main.chapter_item.view.*
import java.lang.StringBuilder

class MyChapterAdapter(internal val context: Context, internal val listchapter: List<Chapter>) :
    RecyclerView.Adapter<MyChapterAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false)
        return MyviewHolder(view)

    }

    override fun getItemCount(): Int {
        return listchapter.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.textchapternumber.text=StringBuilder(listchapter[position].Name)

        holder.setonclick(object :IRecyclerItemClick{
            override fun onclick(view: View, position: Int) {
                //
            }

        })

    }

    class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            IRecyclerItemClick.onclick(v!!, adapterPosition)
        }

        internal val textchapternumber: TextView
        internal lateinit var IRecyclerItemClick: IRecyclerItemClick
        fun setonclick(iclick: IRecyclerItemClick) {
            this.IRecyclerItemClick = iclick
        }

        init {
            textchapternumber = itemView.findViewById(R.id.text_chapter) as TextView
            itemView.setOnClickListener(this)

        }


    }
}