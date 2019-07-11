package com.jmdevelopers.firebasecomicreader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlin.contracts.contract

class MyViewPageAdapter(internal var baseContext: Context?,internal var links: List<String>): PagerAdapter() {
   internal var inflater:LayoutInflater
    init {
        inflater= LayoutInflater.from(baseContext)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return links.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
        // diz que o object é uma view
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image_layout=inflater.inflate(R.layout.view_page_item,container,false)
        val page_image=image_layout.findViewById(R.id.photo_view) as PhotoView
        Picasso.get().load(links[position]).into(page_image)
        container.addView(image_layout!!)
        return image_layout


    }
}
