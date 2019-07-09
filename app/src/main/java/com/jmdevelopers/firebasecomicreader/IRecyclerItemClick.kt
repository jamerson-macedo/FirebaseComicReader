package com.jmdevelopers.firebasecomicreader

import android.view.View

interface IRecyclerItemClick {
    fun onclick(view :View,position : Int)
}