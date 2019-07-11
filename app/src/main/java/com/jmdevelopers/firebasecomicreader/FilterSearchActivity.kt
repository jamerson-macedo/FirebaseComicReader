package com.jmdevelopers.firebasecomicreader

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jmdevelopers.firebasecomicreader.adapter.MyComicAdapter
import com.jmdevelopers.firebasecomicreader.model.Comic
import kotlinx.android.synthetic.main.activity_filter_search.*
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.dialog_search.*
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class FilterSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_search)
        // inflando o menu
        bottom_app_bar.inflateMenu(R.menu.menu)
        bottom_app_bar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filter_action -> showoptionDialog()
                R.id.search_action -> showSearchDialog()
            }
            true
        }
        recycler_filter_chapter.setHasFixedSize(true)
        recycler_filter_chapter.layoutManager = GridLayoutManager(this, 2)
    }

    @SuppressLint("InflateParams")
    private fun showoptionDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Selecione a categoria")
        val inflater = this.layoutInflater
        val filter_layout = inflater.inflate(R.layout.dialog_filter, null)

        val chipGroup = filter_layout.findViewById<View>(R.id.chipgroup) as ChipGroup
        val autocomplete = filter_layout.findViewById<View>(R.id.edt_category) as AutoCompleteTextView
        autocomplete.threshold = 3
        autocomplete.setAdapter(ArrayAdapter(this, android.R.layout.select_dialog_item, Common.categories))
        autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                autocomplete.setText("")
                val chip = inflater.inflate(R.layout.item_chip, null) as Chip
                chip.text = (view as TextView).text
                chip.setOnCloseIconClickListener { view ->
                    chipgroup.removeView(view)
                }
                chipGroup.addView(chip)

            }
        alertDialog.setView(filter_layout)
        alertDialog.setPositiveButton("FILTER") { dialog: DialogInterface?, which: Int ->
            val filter_key = ArrayList<String>()
            val filter_query = StringBuilder("")
            for (k in 0 until chipGroup.childCount) {
                val chip = chipGroup.getChildAt(k) as Chip
                filter_key.add(chip.text.toString())
            }
            filter_key.sort()
            for (key in filter_key) {
                filter_query.append(key).append(",")
            }
            // remove o ultimo .
            filter_query.setLength(filter_query.length - 1)
            fethfiltercategory(filter_query.toString())


        }


        alertDialog.setNegativeButton("CANCEL") { dialog: DialogInterface?, which: Int -> dialog?.dismiss() }
        alertDialog.show()

    }

    private fun fethfiltercategory(query: String) {
        val comic_filter = ArrayList<Comic>()
        for (comic in Common.comiclist) {
            if (comic.Category!=null){
                if(comic.Category!!.contains(query)){
                    comic_filter.add(comic)
                }
            }
        }
        // aqui mostro o recyclerview
        if(comic_filter.size>0){
            recycler_filter_chapter.adapter=MyComicAdapter(this,comic_filter)
        }else{
            Toast.makeText(this,"no result",Toast.LENGTH_LONG).show()
        }

    }

    private fun showSearchDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Selecione a categoria")
        val inflater = this.layoutInflater
        val search_layout = inflater.inflate(R.layout.dialog_search, null)
       val edt_search =search_layout.findViewById<View>(R.id.edt_search) as EditText
        alertDialog.setView(search_layout)
        alertDialog.setNegativeButton("CANCEL") { dialog: DialogInterface?, which: Int -> dialog?.dismiss() }
        alertDialog.setPositiveButton("SEARCH") { dialog: DialogInterface?, which: Int ->
            fetchsearchComic(edt_search.text.toString())
        }

        alertDialog.show()


    }

    private fun fetchsearchComic(search: String) {
        val comic_searched= ArrayList<Comic>()
        for (comic in Common.comiclist) {
            if (comic.Name!=null){
                if(comic.Name!!.contains(search)){
                    comic_searched.add(comic)
                }
            }
        }
        if(comic_searched.size>0){
            recycler_filter_chapter.adapter=MyComicAdapter(this,comic_searched)
        }else{
            Toast.makeText(this,"no result",Toast.LENGTH_LONG).show()
        }



    }
}
