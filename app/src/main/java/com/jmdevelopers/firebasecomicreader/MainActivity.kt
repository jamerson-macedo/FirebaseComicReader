package com.jmdevelopers.firebasecomicreader

import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.jmdevelopers.firebasecomicreader.Service.PicassoImageLoading
import com.jmdevelopers.firebasecomicreader.adapter.MySliderAdapter
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider
import java.util.ArrayList

class MainActivity : AppCompatActivity(), IBannerLoadDoneListener {
    // interface para mostrar quando o banner foi carregado
    override fun onBannerLoadDoneListener(banner: List<String>) {
        // aqui seta o adapter
        slider.setAdapter(MySliderAdapter(banner))

    }

    lateinit var bannerref: DatabaseReference
    lateinit var comicref: DatabaseReference
    lateinit var IBannerLoadDoneListener:IBannerLoadDoneListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IBannerLoadDoneListener=this
        // iniciando o db
        bannerref = FirebaseDatabase.getInstance().getReference("Banners")
        comicref = FirebaseDatabase.getInstance().getReference("Comic")

        swiperefresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark)
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
    }

    private fun loadcomic() {

    }

    private fun loadbanner() {
        // add listenerforsingleevent é quando os dados sao estaticos

        bannerref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MainActivity,""+p0.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
              val listbanner=ArrayList<String>()
                for (banner in p0.children){
                    // pegando os dados
                    val imagem=banner.getValue(String::class.java)
                    listbanner.add(imagem!!)
                }
                IBannerLoadDoneListener.onBannerLoadDoneListener(listbanner)
            }
        })
    }
}
