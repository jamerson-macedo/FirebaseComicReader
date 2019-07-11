package com.jmdevelopers.firebasecomicreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jmdevelopers.firebasecomicreader.model.Chapter
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import kotlinx.android.synthetic.main.activity_view_comic.*

class ViewComicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comic)
        // leva para o primiro
        back.setOnClickListener {

            if (Common.chapter_index == 0) {
                // quando for o primeiro
                Log.i("chapterindex", Common.chapter_index.toString())
                Toast.makeText(this, "Voce esta lendo o primeiro capitulo", Toast.LENGTH_LONG).show()
            } else {
                Common.chapter_index--
                // a lista de capitulos e qual o index
                fetchlinks(Common.listchapter[Common.chapter_index])
            }
        }
        next.setOnClickListener {
            // leva para o ultimo capitulo do comic
            if (Common.chapter_index == Common.listchapter.size - 1) {
                Log.i("chapterindexnext", Common.chapter_index.toString())
                Toast.makeText(this, "Voce esta lendo o ultimo capitulo", Toast.LENGTH_LONG).show()
            } else {
                Common.chapter_index++
                // a lista de capitulos e qual o index
                fetchlinks(Common.listchapter[Common.chapter_index])


            }
        }
        fetchlinks(Common.select_chapter!!)
    }

    private fun fetchlinks(chapter: Chapter) {
        if (chapter.Links != null) {
            if(chapter.Links!!.size>0){
                val adapter=MyViewPageAdapter(baseContext, chapter.Links!!)
                viewpage.adapter=adapter
                text_chapter_comic.text=Common.formatstring(Common.select_chapter.Name!!)
                // insert transition
                val bookFlipPageTransformer=BookFlipPageTransformer()
                bookFlipPageTransformer.scaleAmountPercent=10f
                viewpage.setPageTransformer(true,bookFlipPageTransformer)

            }

        } else {
            // se nao tiver capitulos
            Toast.makeText(this, "esse é o ultimo capitulo do autor", Toast.LENGTH_LONG).show()

        }


    }
}
