package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComplaintAdapter(private val complaintList: List<ComplaintData>) :RecyclerView.Adapter<ComplaintAdapter.ComplaintHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ComplaintHolder(itemView)
    }

    override fun getItemCount(): Int {
        return complaintList.size
    }

    override fun onBindViewHolder(holder: ComplaintHolder, position: Int) {
        val currentItem = complaintList[position]
        holder.bind(currentItem)
    }

    inner class ComplaintHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
        val status : TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(item: ComplaintData) {
            title.text = item.title
            description.text = item.desc
            status.text = item.status
        }
    }
}