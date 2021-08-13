package com.crudoperation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProjectAdaptor(private val dataSet: ArrayList<DataClass>):RecyclerView.Adapter<ProjectAdaptor.MyViewHolder>() {

    inner class MyViewHolder(var myView:View):RecyclerView.ViewHolder(myView) {
        var instanceOfFirstName: TextView = myView.findViewById(R.id.firstNameTextViewId)
        var instanceOfLastName: TextView = myView.findViewById(R.id.lastNameTextViewId)
        var instanceOfEmail: TextView = myView.findViewById(R.id.emailTextViewId)
        var instanceOfPhoneNumber: TextView = myView.findViewById(R.id.phoneNumberTextViewId)
        var instanceOfImage: TextView = myView.findViewById(R.id.imageViewId)



        fun bindingFunction(dataclass:DataClass){
            instanceOfFirstName.text = dataclass.firstName
            instanceOfLastName.text = dataclass.lastName
            instanceOfEmail.text = dataclass.email
            instanceOfPhoneNumber.text = dataclass.phoneNumber
            instanceOfImage.text = dataclass.abbreviation
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /**
         * hold each views by its position, what u need is the info in it, you get the position and u pass your data class in it
         */
        holder.bindingFunction(dataSet[position])

        holder.myView.setOnClickListener{
            val intent = Intent(holder.myView.context, MainActivity3::class.java)
            intent.putExtra("FIRSTNAME", dataSet[position].firstName)
            intent.putExtra("LASTNAME", dataSet[position].lastName)
            intent.putExtra("PHONENUMBER", dataSet[position].phoneNumber)
            intent.putExtra("EMAIL", dataSet[position].email)
            intent.putExtra("ID", dataSet[position].id)
            holder.myView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}