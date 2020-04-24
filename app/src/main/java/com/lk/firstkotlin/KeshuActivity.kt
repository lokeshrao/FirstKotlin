package com.lk.firstkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lk.firstkotlin.database.DBHelper
import kotlinx.android.synthetic.main.activity_piddi.*

class KeshuActivity : AppCompatActivity() {
    lateinit var listItem :ArrayList<String>
    lateinit var dbhelper:DBHelper

    lateinit var addItem : Button
    lateinit var permissionsBtn : Button
    lateinit var addItemBox : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keshu)
        permissionsBtn=findViewById(R.id.permissions)
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

            permissionsBtn.setOnClickListener(View.OnClickListener {
                val send_sms = ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
                val record_audio = ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)
                val read_storage = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                val camera = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                       Log.e("permission",send_sms.toString()+"   "+record_audio.toString()+"  ")
                if (send_sms!= PackageManager.PERMISSION_GRANTED && record_audio!= PackageManager.PERMISSION_GRANTED && read_storage!= PackageManager.PERMISSION_GRANTED && camera!= PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    val MY_PERMISSIONS_REQUEST_READ_CONTACTS:Int =0
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA),
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS)
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.

                } else {
                  Toast.makeText(this,"Already have permission",Toast.LENGTH_LONG).show()
                }

            })
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
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            0 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this,"Permission granted",Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this,"Permisson not granted",Toast.LENGTH_LONG).show()

                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}

