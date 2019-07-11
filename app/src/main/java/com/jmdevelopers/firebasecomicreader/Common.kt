package com.jmdevelopers.firebasecomicreader

import com.jmdevelopers.firebasecomicreader.model.Chapter
import com.jmdevelopers.firebasecomicreader.model.Comic

object Common {
    fun formatstring(name: String): String {
        val finalresult=StringBuilder(if(name.length>15)name.substring(1,15)+"..." else name)
        return finalresult.toString()

    }

    var categories = arrayOf("Action", "Adult", "Adventure", "Comedy", "Completed", "Cooking", "Doujinshi", "Drama", "Drop", "Ecchi", "Fantasy", "Gender bender", "Harem", "Historical", "Horror", "Jose", "Latest", "Manhua", "Manhwa", "Material arts", "Mature", "Mecha", "Medical", "Mystery", "Newest", "One shot", "Ongoing", "Psychological", "Romance", "School life", "Sci fi", "Seinen", "Shoujo", "Shoujo a", "Shounen", "Shounen ai", "Slice of life", "Smut", "Sports", "Superhero", "Supernatural", "Top Read", "Tragedy", "Webtoons", "Yaoi", "Yuri")
    var chapter_index: Int = -1
    lateinit var select_chapter: Chapter
    lateinit var listchapter: List<Chapter>
    var selected_comic: Comic? = null
    var comiclist: List<Comic> = ArrayList<Comic>()
}