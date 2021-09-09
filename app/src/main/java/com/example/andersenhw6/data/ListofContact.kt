package com.example.andersenhw6.data

import java.util.*
import kotlin.random.Random

object ListContact {
    var contactlist = generate()

    private fun generate(): ArrayList<Contacts> {
        val contactlistPath = arrayListOf<Contacts>()
        for (i in 1..101) {
            contactlistPath.add(
                Contacts(
                    id = Random.nextInt(150),
                    name = "Alex$i",
                    surname = "Ivanov$i",
                    phoneNumber = "89003056412$i",
                    url = "https://picsum.photos/id/1$i/200/300"
                )
            )
        }
        return contactlistPath
    }
}



