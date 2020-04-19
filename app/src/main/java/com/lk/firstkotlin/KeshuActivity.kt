package com.lk.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lk.firstkotlin.R

class KeshuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keshu)
        val actionbar = supportActionBar
        actionbar!!.title = "Keshu"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

