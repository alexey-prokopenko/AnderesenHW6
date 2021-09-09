package com.example.andersenhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.andersenhw6.R
import com.example.andersenhw6.data.Contacts
import com.example.andersenhw6.data.ListContact.contactlist
import com.example.andersenhw6.data.ModelData
import com.example.andersenhw6.databinding.Fragment2Binding


@Suppress("UNREACHABLE_CODE")
class Fragment2 : Fragment() {
    private lateinit var binding: Fragment2Binding
    private val data: ModelData by activityViewModels()
    private var newId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        putIdEt()
        saveChanges()
    }

    private fun putIdEt() {

        data.putId.observe(activity as LifecycleOwner, {
            newId = it
        })
        val contact: Contacts? = contactlist.find { it.id == newId }
        with(binding) {
            edName.setText(contact?.name)
            edSurname.setText(contact?.surname)
            edPhone.setText(contact?.phoneNumber)
        }
    }

    private fun saveChanges() {
        with(binding) {
            btSave.setOnClickListener {
                contactlist[newId].name = edName.text.toString()
                contactlist[newId].surname = edSurname.text.toString()
                contactlist[newId].phoneNumber = edPhone.text.toString()
            }
        }
        if (context?.resources?.configuration?.smallestScreenWidthDp!! >= 600) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.holderFragment, Fragment1())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {
        fun newInstance() = Fragment2()
    }
}