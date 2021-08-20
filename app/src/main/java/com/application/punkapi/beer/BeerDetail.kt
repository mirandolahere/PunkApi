package com.application.punkapi.beer
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.application.punkapi.R
import com.application.punkapi.beer.entity.Beer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_beer.*

class BeerDetail:AppCompatActivity() {
    lateinit var beerViewModel: BeerViewModel
    lateinit var beerClass : Beer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_beer)

        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel::class.java)


        initViews()

    }

    private fun initViews() {
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(intent.getSerializableExtra("OBJECT") !=null) {
            val beer = intent.getSerializableExtra("OBJECT") /*LECTURA DE OBJECTO SELECCIONADO*/
            val gson = Gson()
            beerClass = gson.fromJson(beer.toString(), Beer::class.java)

            ShowDetails()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun ShowDetails() {

        tvNameBeer.text = beerClass.name
        tvDescription.text = beerClass.description
        tvFermentation.text = beerClass.method.fermentation.temp.value.toString() + " " + beerClass.method.fermentation.temp.unit
        tvPercentage.text = beerClass.ABV.toString() + " %"
        tvTagLineDetail.text = beerClass.tagline
        DownloadImage(imgBeer).execute(beerClass.image_url)


    }

    private inner class DownloadImage(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {

        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
