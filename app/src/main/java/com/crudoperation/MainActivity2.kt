package com.crudoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var instanceOfBackButton : Button
    private lateinit var instanceOfSaveButton : Button
    private lateinit var database: DatabaseReference

    private lateinit var instanceOfInputFirstName: TextInputEditText
    private lateinit var instanceOfInputLastName: TextInputEditText
    private lateinit var instanceOfInputEmail: TextInputEditText
    private lateinit var instanceOfInputNumber: TextInputEditText
    private lateinit var instanceOfInputImageAddress: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        database = Firebase.database.reference

        instanceOfBackButton = findViewById(R.id.exidButtonId)
        instanceOfSaveButton = findViewById(R.id.createButtonId)
        instanceOfInputFirstName = findViewById(R.id.inputFirstNameId)
        instanceOfInputLastName = findViewById(R.id.inputLastNameId)
        instanceOfInputEmail = findViewById(R.id.enterEmailId)
        instanceOfInputNumber = findViewById(R.id.enterNumberId)
        instanceOfInputImageAddress = findViewById(R.id.enterImageAddressId)



        instanceOfBackButton.setOnClickListener { backToMainActivityForAutomaticRefresh() }


        instanceOfSaveButton.setOnClickListener {
            functionSaveData()
            backToMainActivityForAutomaticRefresh()
            Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_LONG).show()
        }


    }

    private fun backToMainActivityForAutomaticRefresh() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun functionSaveData() {
        val grabInputFirstName = instanceOfInputFirstName.text.toString()
        val grabInputLastName = instanceOfInputLastName.text.toString()
        val grabInputEmail = instanceOfInputEmail.text.toString()
        val grabInputNumber = instanceOfInputNumber.text.toString()


        val abbreviation = grabInputFirstName[0].uppercaseChar().toString() + grabInputLastName[0].uppercaseChar().toString()
        val grabInputImageAddress = abbreviation


        val newInputContact = DataClass(grabInputFirstName,grabInputLastName,grabInputEmail,grabInputNumber,grabInputImageAddress)

        newInputContact.id = database.push().key

        database.child("contacts").child(newInputContact.id!!).setValue(newInputContact)
    }
}