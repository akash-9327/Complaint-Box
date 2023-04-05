package com.example.myapplication

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // code for dropdown list
        val spinner = binding.spDepartment
        val items = arrayOf(
            "Select Department",
            "Information Technology",
            "Computer Engineering",
            "Civil Engineering",
            "Mechanical Engineering",
            "ExTc Engineering",
            "Electrical Engineering",
            "Chemical Engineering"
        )
        val adapter =
            ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, arg2: Int, arg3: Long) {
                val items1 = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }

        binding.txtAlready.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("Departments")
            val name = binding.etName.text
            val eno = binding.etEnrollNo.text
            val dept = binding.spDepartment.selectedItem.toString()
            val email = binding.etEmail.text
            val mobile = binding.etPhone.text
            val pass = binding.etPass.text
            val conPass = binding.etConPass.text

            if (name.isBlank() || eno.isBlank() || email.isBlank() || mobile.isBlank() || pass.isBlank() || conPass.isBlank()) {
                Toast.makeText(this, "Please fill the details", Toast.LENGTH_SHORT).show()
            } else if (dept == "Select Department") {
                Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show()
            }else if(conPass.toString() != pass.toString()){
                Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_SHORT).show()
            }
            else{
                database = database.child(dept).child("Students").child(eno.toString())
                database.child("name").setValue(name.toString())
                database.child("email").setValue(email.toString())
                database.child("mobile").setValue(mobile.toString())
                database.child("password").setValue(pass.toString())
            }
        }
    }
}