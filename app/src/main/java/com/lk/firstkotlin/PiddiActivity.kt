package com.lk.firstkotlin

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
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
    lateinit var getSms : Button
    lateinit var listAllMsg :ArrayList<String>
    lateinit var allMsgView :ListView



    lateinit var addItemBox : EditText
    lateinit var musicPlayer : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piddi)
        getSms=findViewById(R.id.getSms)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        addItem = findViewById(R.id.button)
        addItemBox = findViewById(R.id.editText)
        musicPlayer = findViewById(R.id.button2)
        allMsgView = findViewById(R.id.msgListview)


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
        getSms.setOnClickListener(View.OnClickListener {
            listAllMsg= ArrayList()
            val uriSMSURI: Uri = Uri.parse("content://sms/inbox")
            val cur: Cursor? = contentResolver.query(uriSMSURI, null, null, null, null)

            while (cur != null && cur.moveToNext()) {
                val address: String = cur.getString(cur.getColumnIndex("address"))
                val body: String = cur.getString(cur.getColumnIndexOrThrow("body"))
                listAllMsg.add("Number: $address .Message: $body")
            }

            if (cur != null) {
                cur.close()
            }
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listAllMsg)
            allMsgView.setAdapter(arrayAdapter);



        })
        allMsgView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->
            ForegroundService.startService(this, "Foreground Service is running...")
        }

        addItem.setOnClickListener(View.OnClickListener {

             listItem.add(addItemBox.text.toString())
            addItemBox.text = null
            arrayAdapter.notifyDataSetChanged()


        })

        musicPlayer.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this,MusicPlayer::class.java))

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
