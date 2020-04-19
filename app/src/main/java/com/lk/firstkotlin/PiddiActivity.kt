package com.lk.kotlindemo1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lk.firstkotlin.R

class PiddiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piddi)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }
        val actionbar = supportActionBar
        actionbar!!.title = "Piddi"
        actionbar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
