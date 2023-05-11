package com.example.myapplication

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var database: DatabaseReference

    private lateinit var complaintList: ArrayList<ComplaintData>
    private lateinit var complaintRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        sharedPreferences =
            this.requireActivity().getSharedPreferences("MyPreference", MODE_PRIVATE)

        complaintRecyclerView = binding.recyclerViews
        complaintRecyclerView.layoutManager = LinearLayoutManager(context)
        complaintRecyclerView.setHasFixedSize(true)
        complaintList = arrayListOf()
        getData()

        return binding.root
    }

    private fun getData() {
        val dept = sharedPreferences.getString("department", null)
        val year = sharedPreferences.getString("year", null)
        database = Firebase.database.reference.child("Departments").child(dept!!)
            .child(year!!).child("Complaints")
        database.get().addOnSuccessListener {
            if (it.exists()) {
                for (i in it.children) {
                    val complaint = i.getValue(ComplaintData::class.java)
                    complaintList.add(complaint!!)
                }
                complaintRecyclerView.adapter = ComplaintAdapter(complaintList)
            }
        }
    }
}

