package com.example.daynightthem.nav.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daynightthem.R
import com.example.daynightthem.databinding.CalendarItemLayoutBinding
import com.example.daynightthem.databinding.NoteItemLayoutBinding
import com.example.daynightthem.databinding.ReminderItemLayoutBinding
import com.example.daynightthem.databinding.TodoItemLayoutBinding
import com.example.daynightthem.nav.datasource.model.NoteInfo
import com.example.daynightthem.nav.datasource.model.NoteInfo.Calendar
import com.example.daynightthem.nav.datasource.model.NoteInfo.Note
import com.example.daynightthem.nav.datasource.model.NoteInfo.Reminder
import com.example.daynightthem.nav.datasource.model.NoteInfo.TodoData
import com.example.daynightthem.nav.datasource.model.NoteInfoDiffCallback

class MyNoteAdapter(private val onItemClick: (view: CardView, email: NoteInfo?) -> Unit) :
    ListAdapter<NoteInfo, BaseViewHolder>(NoteInfoDiffCallback) {

//    var tracker: SelectionTracker<Long>? = null

//    init {
//        setHasStableIds(true)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var holder: BaseViewHolder? = null

        when (viewType) {
            R.layout.note_item_layout -> {
                holder = MyNotesViewHolder(
                    NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemClick
                )
            }
            R.layout.todo_item_layout -> {
                holder = MyToDosViewHolder(
                    TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemClick
                )

            }
            R.layout.calendar_item_layout -> {
                holder =
                    MyCalenderViewHolder(
                        CalendarItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        onItemClick
                    )

            }
            R.layout.reminder_item_layout -> {
                holder =
                    MyReminderViewHolder(
                        ReminderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        onItemClick
                    )

            }
            else -> {
                holder = MyNotesViewHolder(
                    NoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemClick
                )
            }

        }

        return holder

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Note -> R.layout.note_item_layout
            is Calendar -> R.layout.calendar_item_layout
            is TodoData -> R.layout.todo_item_layout
            is Reminder -> R.layout.reminder_item_layout
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val noteData = getItem(position)

        holder.bind(noteData)
    }

    class MyNotesViewHolder(val binding: NoteItemLayoutBinding, onItemClick: (view: CardView, email: NoteInfo?) -> Unit) :
        BaseViewHolder(binding.root) {


        init {
            binding.cardView.setOnClickListener {
                onItemClick(binding.cardView, binding.note)
            }
            binding.cardView.setOnLongClickListener {
                binding.cardView.isChecked = true
                return@setOnLongClickListener true
            }
        }

        override fun bind(item: NoteInfo) {

            binding.note = item as Note
        }

//        override fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
//            object : ItemDetailsLookup.ItemDetails<Long>() {
//                override fun getPosition(): Int = bindingAdapterPosition
//                override fun getSelectionKey(): Long = itemId
//            }

    }

    class MyToDosViewHolder(val binding: TodoItemLayoutBinding, onItemClick: (view: CardView, email: NoteInfo?) -> Unit) :
        BaseViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                onItemClick(binding.cardView, binding.todo)
            }
            binding.cardView.setOnLongClickListener {
                binding.cardView.isChecked = true
                return@setOnLongClickListener true
            }
        }
        override fun bind(item: NoteInfo) {
            binding.todo = item as TodoData

        }

//        override fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
//            object : ItemDetailsLookup.ItemDetails<Long>() {
//                override fun getPosition(): Int = bindingAdapterPosition
//                override fun getSelectionKey(): Long = itemId
//            }

    }

    class MyCalenderViewHolder(val binding: CalendarItemLayoutBinding, onItemClick: (view: CardView, email: NoteInfo?) -> Unit) :
        BaseViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                onItemClick(binding.cardView, binding.calendar)
            }
            binding.cardView.setOnLongClickListener {
                binding.cardView.isChecked = true
                return@setOnLongClickListener true
            }
        }

        override fun bind(item: NoteInfo) {

            binding.calendar = item as Calendar

        }

//        override fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
//            object : ItemDetailsLookup.ItemDetails<Long>() {
//                override fun getPosition(): Int = bindingAdapterPosition
//                override fun getSelectionKey(): Long = itemId
//            }

    }

    class MyReminderViewHolder(val binding: ReminderItemLayoutBinding, onItemClick: (view: CardView, email: NoteInfo?) -> Unit) :
        BaseViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                onItemClick(binding.cardView, binding.reminder)
            }
            binding.cardView.setOnLongClickListener {
                binding.cardView.isChecked = true
                return@setOnLongClickListener true
            }
        }

        override fun bind(item: NoteInfo) {
            binding.reminder = item as Reminder

        }

//        override fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
//            object : ItemDetailsLookup.ItemDetails<Long>() {
//                override fun getPosition(): Int = bindingAdapterPosition
//                override fun getSelectionKey(): Long = itemId
//            }

    }

}

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: NoteInfo)
}