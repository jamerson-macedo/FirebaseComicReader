package com.jmdevelopers.firebasecomicreader

import com.jmdevelopers.firebasecomicreader.model.Comic

interface IComicLoadDoneListener {
    fun onComicLoadDoneListener(comic:List<Comic>)
}