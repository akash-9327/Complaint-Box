package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCreateAcc.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)

        }

        binding.btnLogin.setOnClickListener{
            val eno = binding.etUsername.text
            val pass = binding.etPassword.text

            if (eno.isBlank() || pass.isBlank()){
                Toast.makeText(this,"Please fill the details",Toast.LENGTH_SHORT).show()
            }else{
                checkEnoPass(eno.toString(),pass.toString())
            }
        }
    }

    private fun checkEnoPass(eno: String, pass: String) {
        database = FirebaseDatabase.getInstance().getReference("Departments")

        database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var isStudentFound = false
                var studentData:StudentData? = StudentData()

                for (departmentSnapshot in snapshot.children){
                    for (yearSnapshot in departmentSnapshot.children){
                        if (yearSnapshot.child("Students").child(eno).exists()){
                            studentData = yearSnapshot.child("Students").child(eno).getValue(StudentData::class.java)
                            if (studentData?.password == pass){
                                isStudentFound = true
                                break
                            }
                        }
                    }
                }

                if (isStudentFound){
                    Toast.makeText(this@LoginActivity,"Login Successful! ${studentData?.name}",Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@LoginActivity,"Student not found or Incorrect Password",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}