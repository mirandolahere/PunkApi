package com.application.punkapi.beer

import android.os.AsyncTask
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.punkapi.beer.entity.Beer
import com.application.punkapi.helper.Helper

class BeerViewModel :ViewModel(){

    val beerDataModel: BeerDataModel =  BeerDataModel()
    val messageResult: MutableLiveData<String> = MutableLiveData()
    val pgbVisibility: MutableLiveData<Int> = MutableLiveData()
    val listBeer: MutableLiveData<List<Beer>> = MutableLiveData()


    init {
        setupObservers()
    }

    private fun setupObservers() {
        beerDataModel.responseLiveData.observeForever {
                it?.let {
                    onRetrieveFinish()
                    listBeer.value = it

                }
        }

        beerDataModel.messageLiveData.observeForever {
            it?.let {
                onRetrieveFinish()
                messageResult.value = it

            }
        }
    }

    private fun onRetrieveFinish() {
        pgbVisibility.value = View.GONE
    }
    private fun onRetrieveLoginUser() {
        pgbVisibility.value = View.VISIBLE
    }

    fun listBeer() {
        onRetrieveLoginUser()

        listBeerTask(this).execute()
    }

    fun searchBeer(
        name: String
    ) {
        onRetrieveLoginUser()

        searchBeerTask(this, name).execute()
    }


    private class listBeerTask internal constructor(
        private var viewModel: BeerViewModel
        )
        : AsyncTask<Void, Void, Boolean>() {


        override fun doInBackground(vararg voids: Void): Boolean? {
            return Helper.isOnline()
        }

        override fun onPostExecute(aBoolean: Boolean?) {

            super.onPostExecute(aBoolean)
                if (aBoolean == true)
            viewModel.beerDataModel.listBeer()
            else
                viewModel.messageResult.setValue("No tienes acceso a internet")
        }
    }


    private class searchBeerTask internal constructor(
        private var viewModel: BeerViewModel,
        private var name : String
    )
        : AsyncTask<Void, Void, Boolean>() {


        override fun doInBackground(vararg voids: Void): Boolean? {
            return Helper.isOnline()
        }

        override fun onPostExecute(aBoolean: Boolean?) {

            super.onPostExecute(aBoolean)
             if (aBoolean == true)
            viewModel.beerDataModel.searchBeer(name)
            else
                viewModel.messageResult.setValue("No tienes acceso a internet")
        }
    }
}