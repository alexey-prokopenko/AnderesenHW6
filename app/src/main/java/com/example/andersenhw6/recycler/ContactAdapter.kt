package com.example.andersenhw6.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andersenhw6.R
import com.example.andersenhw6.data.Contacts
import com.example.andersenhw6.databinding.ContactItemBinding
import com.squareup.picasso.Picasso


class ContactAdapter(
    private var adapterList: ArrayList<Contacts>,
    private val onContactClicked: (id: Int) -> Unit,
    private val onLongContactClicked: (id: Int) -> Unit
) :
    RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    class ContactHolder(
        item: View,
        onContactClicked: (id: Int) -> Unit,
        onLongContactClicked: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(item) {

        private val binding = ContactItemBinding.bind(item)
        private val imageView = binding.imageView2
        private var itemtId: Int? = null

        init {
            item.setOnClickListener {
                itemtId?.let { onContactClicked(it) }
            }
            item.setOnLongClickListener {
                itemtId?.let { onLongContactClicked(it) }
                return@setOnLongClickListener true
            }
        }

        fun bind(contcat: Contacts) {
            itemtId = contcat.id
            binding.textView.text = contcat.name
            Picasso.get().load(contcat.url).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactHolder(view, onContactClicked, onLongContactClicked)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int {
        return (adapterList.size)
    }

    fun filteredlist(filteredContacts: ArrayList<Contacts>) {
        this.adapterList = filteredContacts
        notifyDataSetChanged()
    }

    fun removedList(removedContacts: ArrayList<Contacts>) {
        this.adapterList = removedContacts
        notifyDataSetChanged()
    }
}
