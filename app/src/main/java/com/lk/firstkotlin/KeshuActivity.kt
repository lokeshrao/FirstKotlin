package com.lk.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lk.firstkotlin.database.DBHelper
import kotlinx.android.synthetic.main.activity_piddi.*

class KeshuActivity : AppCompatActivity() {
    lateinit var listItem :ArrayList<String>
    lateinit var dbhelper:DBHelper

    lateinit var addItem : Button
    lateinit var addItemBox : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keshu)

        dbhelper= DBHelper(this)
       listItem=dbhelper.getAllUser()
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

                var result= dbhelper.insertUser(UserModel(name = editText.text.toString()))
                Log.e("this",result.toString())
                var data=dbhelper.getAllUser()
                var users = dbhelper.getAllUser()
//                users.forEach {

                    Log.e("thisass",data.toString())


//                }

                listItem.add(addItemBox.text.toString())
                addItemBox.text = null
                arrayAdapter.notifyDataSetChanged()


            })
        val actionbar = supportActionBar
        actionbar!!.title = "Keshu"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

