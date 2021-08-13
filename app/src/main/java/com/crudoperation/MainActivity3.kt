package com.crudoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity3 : AppCompatActivity() {
    private lateinit var getFirstName:TextView
    private lateinit var getLastName:TextView
    private lateinit var getEmail:TextView
    private lateinit var getNumber:TextView
    private lateinit var instanceOfEditButton: Button
    private lateinit var instanceOfDeleteButton:Button
    private lateinit var instanceOfPhone:ImageView
    lateinit var instanceOfIdInOnCreateFunction:String
    lateinit var instanceOfPutFirstName:String
    lateinit var instanceOfPutLastName:String
    lateinit var instanceOfPutEmail:String
    lateinit var instanceOfPutNumber:String
    var requestPhoneCall = 1
    var myDataBase = FirebaseDatabase.getInstance().getReference("contacts")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)



        getFirstName = findViewById(R.id.textView2)
        getLastName = findViewById(R.id.textView3)
        getEmail = findViewById(R.id.textView4)
        getNumber = findViewById(R.id.textView5)
        instanceOfEditButton = findViewById(R.id.editButtonId)
        instanceOfDeleteButton = findViewById(R.id.deleteButtonId)
        instanceOfPhone = findViewById(R.id.phoneId)


        instanceOfPutFirstName = intent.getStringExtra("FIRSTNAME").toString()
        instanceOfPutLastName = intent.getStringExtra("LASTNAME").toString()
        instanceOfPutEmail = intent.getStringExtra("EMAIL").toString()
        instanceOfPutNumber = intent.getStringExtra("PHONENUMBER").toString()
        instanceOfIdInOnCreateFunction = intent.getStringExtra("ID").toString()

        getFirstName.text = instanceOfPutFirstName
        getLastName.text = instanceOfPutLastName
        getEmail.text = instanceOfPutEmail
        getNumber.text = instanceOfPutNumber

        instanceOfDeleteButton.setOnClickListener {
            deleteFunction()
            finish()
        }

        instanceOfEditButton.setOnClickListener { editFunction() }
//
//        instanceOfPhone.setOnClickListener {
//            checkForPhonePermission()
//        }

    }

    private fun editFunction() {
        var intent = Intent(this, EditActivity::class.java)
        intent.putExtra("FIRSTNAMETOEDIT", instanceOfPutFirstName)
        intent.putExtra("LASTNAMETOEDIT", instanceOfPutLastName)
        intent.putExtra("EMAILTOEDIT", instanceOfPutEmail)
        intent.putExtra("NUMBERTOEDIT", instanceOfPutNumber)
        intent.putExtra("UPDATEID", instanceOfIdInOnCreateFunction)

        startActivity(intent)
        
    }

    private fun deleteFunction() {
        myDataBase.child(instanceOfIdInOnCreateFunction).setValue(null)
    }

//    private fun checkForPhonePermission() {
//        val i = Intent(Intent.ACTION_CALL)
//        if (ActivityCompat.checkSelfPermission(this@MainActivity3, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this@MainActivity3, arrayOf(Manifest.permission.CALL_PHONE), requestPhoneCall);
//        }
//        else {
//            i.data = Uri.parse("tel:" + getNumber.text.toString())
//            startActivity(i)
//        }
//    }

}
