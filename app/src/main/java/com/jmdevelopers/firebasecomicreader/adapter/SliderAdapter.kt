package com.jmdevelopers.firebasecomicreader.adapter

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MySliderAdapter(private val bannerlist :List<String>) : SliderAdapter() {
    override fun getItemCount(): Int {
        return bannerlist.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder!!.bindImageSlide(bannerlist[position])
    }


}