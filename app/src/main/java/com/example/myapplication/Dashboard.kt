package com.example.myapplication


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class Dashboard : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dahs)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.nav_home ->{

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.recyclerview,HomeFragment())
                        .commit()
                    Toast.makeText(this@Dashboard,"Home" ,Toast.LENGTH_SHORT).show()
                   }

                R.id.nav_profile ->
                {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.recyclerview,ProfileFragment())
                        .commit()
                    Toast.makeText(this@Dashboard,"Profile" ,Toast.LENGTH_SHORT).show()
                }
                R.id.nav_status ->
            {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.recyclerview,StatusFragment())
                    .commit()
                Toast.makeText(this@Dashboard,"Status" ,Toast.LENGTH_SHORT).show()
            }
                R.id.nav_settings ->
                {  supportFragmentManager.beginTransaction()
                    .replace(R.id.recyclerview,SettingFragment())
                    .commit()
                    Toast.makeText(this@Dashboard,"Settings" ,Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


}







