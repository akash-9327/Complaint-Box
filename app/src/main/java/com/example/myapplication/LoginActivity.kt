package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference("Departments")
        sharedPreferences = this.getSharedPreferences("MyPreference", MODE_PRIVATE)

        // Load the saved username and password from SharedPreferences
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)
        if (savedUsername != null && savedPassword != null) {
            binding.etUsername.setText(savedUsername)
            binding.etPassword.setText(savedPassword)
            checkEnoPass(savedUsername, savedPassword)
        }

        binding.txtCreateAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val eno = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (eno.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Please fill the details", Toast.LENGTH_SHORT).show()
            } else {
                // Save username and password to SharedPreferences
                sharedPreferences.edit().apply {
                    putString("username", eno)
                    putString("password", pass)
                    apply()
                }
                checkEnoPass(eno, pass)
            }
        }
    }


    private fun checkEnoPass(eno: String, pass: String) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var isStudentFound = false
                var studentData: StudentData? = null

                for (departmentSnapshot in snapshot.children) {
                    for (yearSnapshot in departmentSnapshot.children) {
                        studentData = yearSnapshot.child("Students").child(eno).getValue<StudentData>()
                        if (studentData != null && studentData.password == pass) {
                            isStudentFound = true
                            val intent = Intent(this@LoginActivity, Dashboard::class.java)
                            startActivity(intent)
                            finish()
                            break
                        }
                    }
                    if (isStudentFound) {
                        break
                    }
                }

                if (isStudentFound) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successful! ${studentData?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Student not found or Incorrect Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }



}

