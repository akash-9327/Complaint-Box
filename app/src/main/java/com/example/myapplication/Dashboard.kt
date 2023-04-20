@file:Suppress("DEPRECATION")

package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityDahsBinding

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDahsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDahsBinding.inflate(layoutInflater)
        setContentView(binding.root)
         replaceFragment(HomeFragment())
        // Initialize the BottomNavigationView and set the listener

  binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_status -> {
                    replaceFragment(StatusFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_settings -> {
                    replaceFragment(SettingFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun replaceFragment(fragment:Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layouts,fragment)
        fragmentTransaction.commit()
    }
}








