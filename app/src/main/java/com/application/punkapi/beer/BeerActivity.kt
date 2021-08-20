package com.application.punkapi.beer
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.punkapi.R
import com.application.punkapi.beer.adapter.BeerAdapter
import com.application.punkapi.beer.entity.Beer
import com.application.punkapi.beer.listener.BeerItemListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_beer.*

class BeerActivity:AppCompatActivity() , BeerItemListener {
    lateinit var beerViewModel: BeerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer)

        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel::class.java)

        listBeer()
        setUpObservers()
        setUpViews()

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    private fun setUpObservers()
    {
        beerViewModel.listBeer.observe(this, Observer {

            it?.let {
                /*RETORNO DE DATOS*/
                pgbLoading.visibility = View.GONE
                rvBeer.adapter = (BeerAdapter(this, it))
                rvBeer.layoutManager = LinearLayoutManager(this)
            }
        })

    }
    private fun setUpViews() {

        etsearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                filter(s.toString());

            }
        })
    }
    private fun filter(s: String)
    {
        beerViewModel.searchBeer(s)


    }

    private fun listBeer() {
        pgbLoading.visibility = View.VISIBLE
        beerViewModel.listBeer()
    }

    override fun ItemClickListener(position: Beer?) {
        var gson =  Gson()
        var jsonClass = gson.toJson(position)
        /*SELECCION DE UN ITEM Y REDIRIGE A SU DETALLE*/
        val intent = Intent(this, BeerDetail::class.java)
        intent.putExtra("OBJECT", jsonClass)
        startActivity(intent)

    }


}
