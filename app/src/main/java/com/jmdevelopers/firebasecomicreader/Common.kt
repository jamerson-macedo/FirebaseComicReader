package com.jmdevelopers.firebasecomicreader

import com.jmdevelopers.firebasecomicreader.model.Chapter
import com.jmdevelopers.firebasecomicreader.model.Comic

object Common {
    lateinit var listchapter: List<Chapter>
    var selected_comic: Comic?=null
    var comiclist: List<Comic> = ArrayList<Comic>()
}