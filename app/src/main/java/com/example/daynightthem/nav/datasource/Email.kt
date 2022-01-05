
package com.example.daynightthem.nav.datasource

import androidx.recyclerview.widget.DiffUtil

/**
 * A simple data class to represent an Email.
 */
data class Email(
    val id: Long,
    val subject: String = "",
    val body: String = "",
    var isImportant: Boolean = false,
    var isStarred: Boolean = false,
) {

}

object EmailDiffCallback : DiffUtil.ItemCallback<Email>() {
    override fun areItemsTheSame(oldItem: Email, newItem: Email) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Email, newItem: Email) = oldItem == newItem
}


