package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCreateAcc.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val username = binding.etUsername.text
            val pass = binding.etPassword.text

            if (username.isBlank() || pass.isBlank()){
                Toast.makeText(this,"Please fill the details",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }
}