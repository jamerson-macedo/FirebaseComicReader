package com.jmdevelopers.firebasecomicreader

import com.jmdevelopers.firebasecomicreader.model.Chapter
import com.jmdevelopers.firebasecomicreader.model.Comic

object Common {
    fun formatstring(name: String): String {
        val finalresult=StringBuilder(if(name.length>15)name.substring(1,15)+"..." else name)
        return finalresult.toString()

    }

    var chapter_index: Int = -1
    lateinit var select_chapter: Chapter
    lateinit var listchapter: List<Chapter>
    var selected_comic: Comic? = null
    var comiclist: List<Comic> = ArrayList<Comic>()
}