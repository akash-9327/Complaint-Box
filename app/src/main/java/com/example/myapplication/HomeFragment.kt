package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(), CardAdapter.OnItemClickListener {

    private lateinit var adapter: CardAdapter
    private lateinit var dataList: List<CardData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViews)


        // Define the data to be displayed
        dataList = listOf(
            CardData("Title 1", "Description 1"),
            CardData("Title 2", "Description 2"),
            CardData("Title 3", "Description 3"),
            CardData("Title 4", "Description 4")
        )

        // Initialize the RecyclerView and its adapter
        adapter = CardAdapter(dataList)
        adapter.setOnItemClickListener(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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

