package com.example.andersenhw6.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andersenhw6.R
import com.example.andersenhw6.data.Contacts
import com.example.andersenhw6.data.ListContact.contactlist
import com.example.andersenhw6.data.ModelData
import com.example.andersenhw6.databinding.Fragment1Binding
import com.example.andersenhw6.recycler.ContactAdapter
import com.example.andersenhw6.recycler.RecyclerDecoration
import java.util.*


class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private val data: ModelData by activityViewModels()
    private val adapter = ContactAdapter(contactlist,
        { id -> openFragment(id) },
        { id -> dialogDelete(id) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment1Binding.inflate(inflater)
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rcView.layoutManager = LinearLayoutManager(context)
        binding.rcView.adapter = adapter
        binding.rcView.addItemDecoration(RecyclerDecoration())
        binding.rcView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun openFragment(id: Int) {
        val screenMode = context?.resources?.configuration?.smallestScreenWidthDp
        if (screenMode!! >= 600) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.holderFragment2, Fragment2.newInstance())
                ?.addToBackStack(null)
                ?.commit()

        } else {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.holderFragment, Fragment2.newInstance())
                ?.addToBackStack(null)
                ?.commit()
        }
        data.putId.value = id
    }

    private fun filter(text: String) {
        val filteredContacts = ArrayList<Contacts>()
        contactlist.filterTo(filteredContacts) {
            it.name.lowercase().contains(text.lowercase())
        }
        adapter.filteredlist(filteredContacts)
    }

    private fun dialogDelete(id: Int) {
        AlertDialog.Builder(context)
            .setMessage("Delete this element?")
            .setPositiveButton("OK") { _, i -> removeContact(id) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun removeContact(id: Int) {
        val removedContacts = ArrayList<Contacts>()
        val contacToRemove = contactlist.find { it.id == id }
        removedContacts.addAll(contactlist)
        removedContacts.remove(contacToRemove)
        adapter.removedList(removedContacts)
    }
}



