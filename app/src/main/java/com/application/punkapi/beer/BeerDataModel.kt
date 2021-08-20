package com.application.punkapi.beer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.application.punkapi.beer.entity.Beer
import com.application.punkapi.retrofit.RetrofitClient
import com.google.gson.Gson
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerDataModel () {
    private val TAG = BeerDataModel::class.java.simpleName
    val responseLiveData = MutableLiveData<List<Beer>>()
    val messageLiveData = MutableLiveData<String>()


    fun listBeer()
    {
        val call =  RetrofitClient.getApiService()?.ListBeer()
        call?.enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                    if (response.isSuccessful) {

                        responseLiveData.value = response.body()
                    } else {

                        messageLiveData.value = "Error de búsqueda"

                    }
                }

                override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                    t.printStackTrace()
                    messageLiveData.value = "Falla en la conexión"
                }
            })
        }

    fun searchBeer(name:String)
    {
        val call =  RetrofitClient.getApiService()?.SearchBeer(name)
        call?.enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                if (response.isSuccessful) {

                    responseLiveData.value = response.body()
                } else {

                    messageLiveData.value = "Error de búsqueda"

                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                t.printStackTrace()
                messageLiveData.value = "Falla en la conexión"
            }
        })
    }

}