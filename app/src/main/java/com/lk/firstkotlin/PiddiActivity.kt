package com.lk.firstkotlin

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_piddi.*


class PiddiActivity : AppCompatActivity() {

    var userList = arrayListOf<String>("pooja","lokesh","piddi","lokesh rao","pooja dassani","lokesh yadav")
     var listItem =
         arrayListOf<String>("Lokesh", "Keshu", "Guddu", "PubG-player", "Rao", "its_lokesh_rao")
    lateinit var addItem : Button
    lateinit var addItemBox : EditText
    lateinit var getMsgBtn : Button
    lateinit var listMsg :ArrayList<String>
    lateinit var listViewMsg :ListView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piddi)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        addItem = findViewById(R.id.button)
        addItemBox = findViewById(R.id.editText)
        getMsgBtn = findViewById(R.id.getMsg)
        listViewMsg = findViewById(R.id.listviewMsg)


        recyclerview_list.layoutManager = LinearLayoutManager(this)
        recyclerview_list.adapter = UserAdapter(userList,this)
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
        getMsgBtn.setOnClickListener(View.OnClickListener {
            listMsg= ArrayList()
            val uriSMSURI: Uri = Uri.parse("content://sms/inbox")
            val cur: Cursor? = contentResolver.query(uriSMSURI, null, null, null, null)

            while (cur != null && cur.moveToNext()) {
                val address: String = cur.getString(cur.getColumnIndex("address"))
                val body: String = cur.getString(cur.getColumnIndexOrThrow("body"))
                listMsg.add("Number: $address .Message: $body")
            }
            Log.e("text",listMsg.toString())
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listMsg)
            listViewMsg.setAdapter(arrayAdapter);

            if (cur != null) {
                cur.close()
            }
        })
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
