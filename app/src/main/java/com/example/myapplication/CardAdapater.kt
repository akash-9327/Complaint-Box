package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CardAdapter(private var dataList: MutableList<CardData>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var mListener: OnItemClickListener? = null
    private lateinit var database: DatabaseReference

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return CardViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addData(data: CardData) {
        // Get a reference to the Firebase database
        database = FirebaseDatabase.getInstance().reference

        // Add the new data to the database
        database.child("card_data").push().setValue(data)

        // Add the new data to the list
        dataList.add(data)

        // Notify the adapter that the data has changed
        notifyDataSetChanged()
    }

    inner class CardViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            listener?.let {
                itemView.setOnClickListener(this)
            }
        }

        fun bind(data: CardData) {
            titleTextView.text = data.name
            descriptionTextView.text = data.email
        }

        override fun onClick(view: View?) {
            view?.let {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    mListener?.onItemClick(position)
                }
            }
        }
    }
}

