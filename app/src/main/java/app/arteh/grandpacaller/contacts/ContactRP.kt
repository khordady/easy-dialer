package app.arteh.grandpacaller.contacts

import android.content.Context
import android.provider.ContactsContract
import app.arteh.grandpacaller.contacts.show.Contact

class ContactRP {
    var contactMList = mutableListOf<Contact>()
    var lazyKey = 0

    fun loadContacts(context: Context) {
        contactMList = mutableListOf()

        val cr = context.contentResolver

        val columns: Array<String> = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP
        )

        val cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, columns, null, null,
            ContactsContract.Contacts.DISPLAY_NAME + "," + ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        if (cursor != null) {
            try {
                val nameIndex =
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val dateIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP)

                while (cursor.moveToNext()) {
                    val name = cursor.getString(nameIndex)
                    val number = cursor.getString(numberIndex).replace(" ", "")
                    val date = cursor.getLong(dateIndex)

                    val contact = Contact(name, number, date, lazyKey++)
                    contactMList.add(contact)
                }
            } finally {
                cursor.close()
            }
        }

        //find and delete repeated phone
        var count = contactMList.size
        var j = 0
        while (j < count) {
            if (j + 1 < count)
                if (contactMList[j].phone == contactMList[j + 1].phone) {
                    contactMList.removeAt(j + 1)
                    j--
                    count--
                }
            j++
        }
    }

    fun getContactName(number: String): Pair<String, String> {
        for (contact in contactMList)
            if (contact.phone == number) return (contact.name to contact.phone)

        return ("" to "")
    }
}