package com.lk.firstkotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_piddi.*

class PiddiActivity : AppCompatActivity() {

     var listItem =
         arrayListOf<String>("Lokesh", "Keshu", "Guddu", "PubG-player", "Rao", "its_lokesh_rao")
    lateinit var addItem : Button
    lateinit var addItemBox : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piddi)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        addItem = findViewById(R.id.button)
        addItemBox = findViewById(R.id.editText)

        listView.adapter = arrayAdapter //ask lokesh why different ids
        listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->
            val selectedItem = adapterView.getItemAtPosition(position) as String
            val itemIdPosition = adapterView.getItemIdAtPosition(position + 1)
            Toast.makeText(
                applicationContext,
                "The name you clicked is $selectedItem its position  is  $itemIdPosition",
                Toast.LENGTH_LONG).show()
        }
        listView.setOnItemLongClickListener { adapterView, view, position: Int, id: Long ->
            val selectedItem = adapterView.getItemAtPosition(position) as String
            val itemIdPosition = adapterView.getItemIdAtPosition(position + 1)
            Toast.makeText(
                applicationContext,
                "The name you clicked is $selectedItem its position  is  $itemIdPosition",
                Toast.LENGTH_LONG).show()
            listItem.remove(selectedItem)
            arrayAdapter.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }

        addItem.setOnClickListener(View.OnClickListener {

             listItem.add(addItemBox.text.toString())
            addItemBox.text = null
            arrayAdapter.notifyDataSetChanged()


        })

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

private fun <E> List<E>.add(e: E) {

}
