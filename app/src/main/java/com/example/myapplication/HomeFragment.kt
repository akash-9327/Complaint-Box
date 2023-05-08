package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(), CardAdapter.OnItemClickListener {

    private lateinit var adapter: CardAdapter
    private lateinit var dataList: MutableList<CardData>
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViews)

        database = Firebase.database.reference

        // Initialize the RecyclerView and its adapter
        dataList = mutableListOf()
        adapter = CardAdapter(dataList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Read data from the Firebase Realtime Database
        database.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (cardSnapshot in snapshot.children) {
                    val cardData = cardSnapshot.getValue(CardData::class.java)
                    if (cardData != null) {
                        dataList.add(cardData)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })

        return view
    }

    override fun onItemClick(position: Int) {
        // Create the fragment that you want to open
        val fragment = ClickOnFragment()

        // Set any necessary arguments for the fragment
        val args = Bundle()
        args.putInt("position", position)
        fragment.arguments = args

        // Use the FragmentManager to replace the current fragment with the new fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.onclick, fragment)
            .addToBackStack(null)
            .commit()
    }
}

