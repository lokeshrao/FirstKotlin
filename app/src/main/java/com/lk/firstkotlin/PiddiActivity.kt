package com.lk.firstkotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_piddi.*

class PiddiActivity : AppCompatActivity() {

    private val listItem =
        arrayOf("Lokesh", "Keshu", "Guddu", "PubG-player", "Rao", "its_lokesh_rao")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piddi)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)

        listView.adapter = arrayAdapter //ask lokesh why different ids
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->

                val selectedItem = adapterView.getItemAtPosition(position) as String
                val itemIdPosition = adapterView.getItemIdAtPosition(position + 1)
                Toast.makeText(
                    applicationContext,
                    "The name you clicked is $selectedItem its position  is  $itemIdPosition",
                    Toast.LENGTH_LONG
                ).show()
            }

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
