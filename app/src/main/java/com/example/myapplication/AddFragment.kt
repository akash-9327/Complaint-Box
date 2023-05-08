package com.example.myapplication

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        sharedPreferences =
            this.requireActivity().getSharedPreferences("MyPreference", MODE_PRIVATE)
        val dept = sharedPreferences.getString("department", null)
        val year = sharedPreferences.getString("year", null)
        val eno = sharedPreferences.getString("username", null)
        val name = sharedPreferences.getString("name", null)
        database = FirebaseDatabase.getInstance().getReference("Departments").child(dept!!)
            .child(year!!).child("Complaints")
        binding.cmpSubmit.setOnClickListener {
            val ref = database.push()
            val title = binding.cmpTitleText.text.toString()
            val desc = binding.cmpDescText.text.toString()
            val complaint = ComplaintData(title, desc, eno!!, name!!)
            ref.setValue(complaint)
            binding.cmpTitleText.setText("")
            binding.cmpDescText.setText("")
        }
        return binding.root
    }
}