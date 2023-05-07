package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val dataList: List<CardData>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var mListener: OnItemClickListener? = null

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

    inner class CardViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            listener?.let {
                itemView.setOnClickListener(this)
            }
        }

        fun bind(data: CardData) {
            titleTextView.text = data.title
            descriptionTextView.text = data.description
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
