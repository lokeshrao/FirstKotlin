package com.lk.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var piddiBtn: Button
    lateinit var keshuBtn: Button
    var abc : Int = 0
    lateinit var hello : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        piddiBtn= findViewById(R.id.userPiddi)
        keshuBtn=findViewById(R.id.userKeshu)
        piddiBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, PiddiActivity::class.java))
        })
        keshuBtn.setOnClickListener(View.OnClickListener {
            keshuBtn.text = "piddi"
            startActivity(Intent(this@MainActivity, KeshuActivity::class.java))
            test()
        })

    }
    fun test(){
        Toast.makeText(this,"hello"+piddiBtn.text,Toast.LENGTH_LONG).show()

    }
}
