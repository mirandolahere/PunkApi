package com.application.punkapi.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.punkapi.R
import com.application.punkapi.beer.BeerActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setUpViews()
    }

    private fun setUpViews() {

        btn_start.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, BeerActivity::class.java))
            finish()
        }
    }

}