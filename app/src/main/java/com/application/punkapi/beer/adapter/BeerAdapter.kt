package com.application.punkapi.beer.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.punkapi.R
import com.application.punkapi.beer.entity.Beer
import com.application.punkapi.beer.listener.BeerItemListener
import kotlin.collections.ArrayList


class BeerAdapter(
    internal var listener: BeerItemListener, listBeer: List<Beer>
) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    private val options =  ArrayList(listBeer)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_beer,
            viewGroup,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BeerAdapter.ViewHolder, i: Int) {
        val name = options[i]

        viewHolder.bindItem(name, i)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgBeer: ImageView = itemView.findViewById(R.id.imgBeer)
        var tvNameBeer: TextView = itemView.findViewById(R.id.tvNameBeer)
        var tvTagLine: TextView = itemView.findViewById(R.id.tvTagLine)
        var tvFirstBrewed: TextView = itemView.findViewById(R.id.tvFirstBrewed)
        var rlDetail: LinearLayout = itemView.findViewById(R.id.rlDetail)


        @SuppressLint("ResourceAsColor")
        fun bindItem(name: Beer, position: Int) {


            DownloadImage(imgBeer).execute(name.image_url)

            tvNameBeer.text = name.name
            tvTagLine.text = name.tagline
            tvFirstBrewed.text = name.first_brewed

            rlDetail.setOnClickListener {
                listener.ItemClickListener(name)
            }


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
    }
}