package com.jmdevelopers.firebasecomicreader

import android.os.Bundle

import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.jmdevelopers.firebasecomicreader.Service.PicassoImageLoading
import com.jmdevelopers.firebasecomicreader.adapter.MyComicAdapter
import com.jmdevelopers.firebasecomicreader.adapter.MySliderAdapter
import com.jmdevelopers.firebasecomicreader.model.Comic
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider
import java.util.ArrayList

class MainActivity : AppCompatActivity(), IBannerLoadDoneListener, IComicLoadDoneListener {
    // é feito essa interface para personalizar eventos dentro dele
    override fun onComicLoadDoneListener(comic: List<Comic>) {
        Common.comiclist = comic
        recycler_commic.adapter = MyComicAdapter(baseContext, comic)
        // texto que fica na barra superior
        textcomic.text = StringBuilder("NEW COMIC(").append(comic.size).append(")")
        if (swiperefresh.isRefreshing) this.swiperefresh.isRefreshing=false
    }
    // interface para mostrar quando o banner foi carregado
    override fun onBannerLoadDoneListener(banner: List<String>) {
        // aqui seta o adapter
        slider.setAdapter(MySliderAdapter(banner))
    }

    lateinit var bannerref: DatabaseReference
    lateinit var comicref: DatabaseReference
    lateinit var IBannerLoadDoneListener: IBannerLoadDoneListener
    lateinit var IComicLoadDoneListener: IComicLoadDoneListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IBannerLoadDoneListener = this
        IComicLoadDoneListener = this
        // iniciando o db
        bannerref = FirebaseDatabase.getInstance().getReference("Banners")
        comicref = FirebaseDatabase.getInstance().getReference("Comic")

        swiperefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swiperefresh.setOnRefreshListener {
            loadbanner()
            loadcomic()
        }
        swiperefresh.post {
            loadbanner()
            loadcomic()
        }
        // iniciando o slider
        Slider.init(PicassoImageLoading())
        recycler_commic.setHasFixedSize(true)
        recycler_commic.layoutManager= GridLayoutManager(this@MainActivity,2)
    }

    private fun loadcomic() {
        comicref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                //Mutable list pode ser removido e alterado
                val list_comic: MutableList<Comic> = ArrayList<Comic>()
                for (comicsnapshot in p0.children) {
                    val comic = comicsnapshot.getValue(Comic::class.java)
                    list_comic.add(comic!!)

                }
                IComicLoadDoneListener.onComicLoadDoneListener(list_comic)

            }


        })

    }

    private fun loadbanner() {
        // add listenerforsingleevent é quando os dados sao estaticos

        bannerref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MainActivity, "" + p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                // lista é somente leitura
                val listbanner = ArrayList<String>()
                for (banner in p0.children) {
                    // pegando os dados
                    val imagem = banner.getValue(String::class.java)
                    listbanner.add(imagem!!)
                }
                IBannerLoadDoneListener.onBannerLoadDoneListener(listbanner)

            }
        })
    }
}
