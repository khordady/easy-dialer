package app.arteh.grandpacaller.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.arteh.grandpacaller.R
import app.arteh.grandpacaller.clog.CLogScreen
import app.arteh.grandpacaller.clog.CLogVM
import app.arteh.grandpacaller.contacts.show.ContactScreen
import app.arteh.grandpacaller.contacts.show.ContactsVM
import app.arteh.grandpacaller.dial.DialPadScreen
import app.arteh.grandpacaller.dial.DialPadVM
import app.arteh.grandpacaller.ui.Divider2
import app.arteh.grandpacaller.ui.PaddingSides
import app.arteh.grandpacaller.ui.noRippleClickable
import app.arteh.grandpacaller.ui.theme.AppColor

private lateinit var mainVM: MainVM

@Composable
fun MainScreen(
    vm: MainVM = viewModel(),
    contactsVM: ContactsVM,
    cLogVM: CLogVM,
    dialPadVM: DialPadVM,
    padding: PaddingSides
) {
    mainVM = vm
    val uiState = vm.uiState.collectAsStateWithLifecycle().value

    Column(
        Modifier
            .fillMaxWidth()
            .padding(
                start = padding.start,
                top = padding.top,
                end = padding.end,
                bottom = padding.bottom
            )
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (uiState.selectedTab) {
                0 -> {
                    ContactScreen(contactsVM)
                    contactsVM.load()
                }

                1 -> DialPadScreen(dialPadVM)

                2 -> {
                    CLogScreen(cLogVM)
                    cLogVM.load()
                }
            }
        }
        Divider2()
        BottomTabs(uiState.selectedTab)
    }
}

@Composable
private fun BottomTabs(selectedTab: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .noRippleClickable({ mainVM.setPage(0) }),
            painter = painterResource(R.drawable.contacts),
            contentDescription = "Contact",
            tint = if (selectedTab == 0) MaterialTheme.colorScheme.primary else AppColor.Gray1.resolve()
        )
        Icon(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .noRippleClickable({ mainVM.setPage(1) }),
            painter = painterResource(R.drawable.dial),
            contentDescription = "Dial",
            tint = if (selectedTab == 1) MaterialTheme.colorScheme.primary else AppColor.Gray1.resolve()
        )
        Icon(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .noRippleClickable({ mainVM.setPage(2) }),
            painter = painterResource(R.drawable.call_log),
            contentDescription = "Call log",
            tint = if (selectedTab == 2) MaterialTheme.colorScheme.primary else AppColor.Gray1.resolve()
        )
    }
}