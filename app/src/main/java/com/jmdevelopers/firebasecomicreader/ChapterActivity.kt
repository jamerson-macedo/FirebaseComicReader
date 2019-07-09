package com.jmdevelopers.firebasecomicreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmdevelopers.firebasecomicreader.adapter.MyChapterAdapter
import com.jmdevelopers.firebasecomicreader.model.Comic
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.chapter_item.*
import java.lang.StringBuilder

class ChapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        toolbar.title=Common.selected_comic!!.Name
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        recycler_chapter.setHasFixedSize(true)
        val lauyoutmanager=LinearLayoutManager(this)
        recycler_chapter.layoutManager=lauyoutmanager
        recycler_chapter.addItemDecoration(DividerItemDecoration(this,lauyoutmanager.orientation))
        // buscar por capitulo
        // passanndo o comic selecionado
        fatchchapter(Common.selected_comic!!)
    }

    private fun fatchchapter(selectedComic: Comic) {
        Common.listchapter=selectedComic.Chapters!!
        text_number.text=StringBuilder("CHAPTER (").append(selectedComic.Chapters!!.size).append(")")
        recycler_chapter.adapter=MyChapterAdapter(this,Common.listchapter)
    }
}
