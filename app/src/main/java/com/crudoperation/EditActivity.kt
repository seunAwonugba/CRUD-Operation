package com.crudoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.stream.Collectors.toMap

class EditActivity : AppCompatActivity() {
    private lateinit var instanceOfEditFirstName: TextView
    private lateinit var instanceOfEditLastName: TextView
    private lateinit var instanceOfEditEmail: TextView
    private lateinit var instanceOfEditNumber: TextView
    private lateinit var instanceOfUpdateButton: TextView
    private lateinit var instanceOfCancelButton: TextView
    private lateinit var instanceOfUpdateKey:String
    var myDataBase = FirebaseDatabase.getInstance().getReference("contacts")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        instanceOfEditFirstName = findViewById(R.id.putFirstNameEditNameId)
        instanceOfEditLastName = findViewById(R.id.putLastNameEditNameId)
        instanceOfEditEmail = findViewById(R.id.putEmailEditEmailId)
        instanceOfEditNumber = findViewById(R.id.putNumbereditNumberId)
        instanceOfUpdateButton = findViewById(R.id.updateButtonId)
        instanceOfCancelButton = findViewById(R.id.cancelButtonId)

        val putEditFirstName = intent.getStringExtra("FIRSTNAMETOEDIT")
        val putEditLastName = intent.getStringExtra("LASTNAMETOEDIT")
        val putEditEmail = intent.getStringExtra("EMAILTOEDIT")
        val putEditNumber = intent.getStringExtra("NUMBERTOEDIT")
        instanceOfUpdateKey = intent.getStringExtra("UPDATEID").toString()

        instanceOfEditFirstName.text = putEditFirstName
        instanceOfEditLastName.text = putEditLastName
        instanceOfEditEmail.text = putEditEmail
        instanceOfEditNumber.text = putEditNumber


        instanceOfUpdateButton.setOnClickListener {
            updateFunction()
            backToLandingPage()
        }



        instanceOfCancelButton.setOnClickListener {
            finish()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        }

    }

    private fun updateFunction() {
        val abbreviation = instanceOfEditFirstName.text[0].uppercaseChar().toString() + instanceOfEditLastName.text[0].uppercaseChar().toString()
        val updateDataVariable = DataClass(instanceOfEditFirstName.text.toString(),instanceOfEditLastName.text.toString()
                ,instanceOfEditEmail.text.toString(), instanceOfEditNumber.text.toString(), abbreviation)
        myDataBase.child(instanceOfUpdateKey).setValue(updateDataVariable)
    }

    private fun backToLandingPage() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this,"Contact successfully updated", Toast.LENGTH_LONG).show()
    }
}