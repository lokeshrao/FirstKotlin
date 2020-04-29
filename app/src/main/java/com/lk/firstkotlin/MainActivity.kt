package com.lk.firstkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var piddiBtn: Button
    lateinit var keshuBtn: Button
    var abc : Int = 0
    lateinit var hello : String
    lateinit var receiver : BroadcastReceiver
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
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context,intent?.action,
                    Toast.LENGTH_LONG).show()
            }

        }

        registerReceiver(receiver,filter)

    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }


    fun test(){
        Toast.makeText(this,"hello"+piddiBtn.text,Toast.LENGTH_LONG).show()

    }
}
