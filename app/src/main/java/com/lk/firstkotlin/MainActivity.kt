package com.lk.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var piddiBtn: Button
    lateinit var keshuBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        piddiBtn= findViewById(R.id.userPiddi)
        keshuBtn=findViewById(R.id.userKeshu)
        piddiBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, PiddiActivity::class.java))
        })
        keshuBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, KeshuActivity::class.java))
        })

    }
}
