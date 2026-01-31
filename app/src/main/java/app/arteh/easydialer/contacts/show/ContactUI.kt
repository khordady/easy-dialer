package app.arteh.easydialer.contacts.show

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.arteh.easydialer.ui.theme.appTypography

@Composable
fun ContactScreen(contactsVM: ContactsVM) {
    val contacts = contactsVM.items.collectAsStateWithLifecycle().value

    Column() {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(contacts, key = { it.key }) {
                ItemContact(it)
            }
        }
    }
}

@Composable
private fun ItemContact(contact: Contact) {
    Row(
        Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .background(
                color = Color.White,
                RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        Column() {
            Text(text = contact.name, style = MaterialTheme.appTypography.h4)
            Text(text = contact.phone, style = MaterialTheme.appTypography.desc)
        }
    }
}