package com.lk.firstkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lk.firstkotlin.database.DBHelper
import kotlinx.android.synthetic.main.activity_piddi.*

class KeshuActivity : AppCompatActivity() {
    lateinit var listItem :ArrayList<String>
    lateinit var dbhelper:DBHelper
    lateinit var listContactsList :ArrayList<String>
    lateinit var addItem : Button
    lateinit var permissionsBtn : Button
    lateinit var getContactsBtn : Button
    lateinit var name : String
    lateinit var phonenumber : String

    lateinit var cursor : Cursor


    lateinit var addItemBox : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keshu)
        permissionsBtn=findViewById(R.id.permissions)
        getContactsBtn=findViewById(R.id.getContacts)


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
                val read_contacts = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
                val read_storage = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                val camera = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                       Log.e("permission",send_sms.toString()+"   "+read_contacts.toString()+"  ")
                if (send_sms!= PackageManager.PERMISSION_GRANTED && read_contacts!= PackageManager.PERMISSION_GRANTED && read_storage!= PackageManager.PERMISSION_GRANTED && camera!= PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    val MY_PERMISSIONS_REQUEST_READ_CONTACTS:Int =0
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.READ_CONTACTS,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA),
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

        getContactsBtn.setOnClickListener(View.OnClickListener {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null)!!;
            Log.e("thisass",cursor.toString())
            listContactsList = ArrayList<String>()
            var listContacts = findViewById<ListView>(R.id.listContacts)
            while (cursor.moveToNext()) {


                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                Log.e("thisass",name+phonenumber)

                listContactsList.add(name + " "  + ":" + " " + phonenumber);
            }
            cursor.close()
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listContactsList)
            listContacts.setAdapter(arrayAdapter);

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

