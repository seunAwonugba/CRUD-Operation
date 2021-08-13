package com.crudoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var instanceOfFloatingButton:FloatingActionButton
    private lateinit var database: DatabaseReference
    private lateinit var instanceOfMyArrayList: ArrayList<DataClass>
    private lateinit var instanceOfRecyclerView: RecyclerView
//    private lateinit var instanceOfAdaptor: ProjectAdaptor
    private lateinit var query : Query



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanceOfFloatingButton = findViewById(R.id.floatingActionButtonId)
        database = Firebase.database.reference
        instanceOfMyArrayList = arrayListOf()
        instanceOfRecyclerView = findViewById(R.id.recyclerViewId)


        instanceOfRecyclerView.layoutManager = LinearLayoutManager(this)


        functionImportDataFromRecyclerView()


        instanceOfFloatingButton.setOnClickListener { functionToNextActivity() }
    }

    private fun functionImportDataFromRecyclerView() {
        query = database.child("contacts")

        query.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        instanceOfMyArrayList.clear()
                        for (childDss in dataSnapshot.children) {
                            val contact = childDss.getValue(DataClass::class.java)
                            if (contact != null) {
                                contact.id = childDss.key
                                instanceOfMyArrayList.add(contact)
                            }
                        }
                        val variableProjectAdaptor = ProjectAdaptor(instanceOfMyArrayList)
                        instanceOfRecyclerView.adapter = variableProjectAdaptor
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Unable to fetch data", Toast.LENGTH_LONG)
                        .show()
                }
            },
        )
    }

    private fun functionToNextActivity() {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }
}