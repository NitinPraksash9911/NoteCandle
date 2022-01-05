package com.example.daynightthem.nav.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.daynightthem.databinding.EmailItemLayoutBinding
import com.example.daynightthem.nav.datasource.Email
import com.example.daynightthem.nav.datasource.EmailDiffCallback
import com.example.daynightthem.nav.home.adapter.EmailAdapter.EmailViewHolder

class EmailAdapter(private val onItemClick: (view: CardView, email: Email?) -> Unit) :
    ListAdapter<Email, EmailViewHolder>(EmailDiffCallback) {

     var tracker: SelectionTracker<Long>? = null
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = EmailItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding, onItemClick = onItemClick)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {

        val email = getItem(position)
        tracker?.let { holder.bind(email, it.isSelected(position.toLong())) }
    }

    inner class EmailViewHolder(
        private val binding: EmailItemLayoutBinding,
        onItemClick: (view: CardView, email: Email?) -> Unit
    ) :
        ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                onItemClick(binding.cardView, binding.email)
            }
            binding.cardView.setOnLongClickListener {
                binding.cardView.isChecked = true
                return@setOnLongClickListener true
            }
        }

        fun bind(email: Email, isActivated:Boolean=false) {
            binding.email = email
            itemView.isActivated = isActivated
            binding.cardView.isChecked= isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }

}