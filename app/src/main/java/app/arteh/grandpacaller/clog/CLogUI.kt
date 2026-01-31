package app.arteh.grandpacaller.clog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CLogScreen(cLogVM: CLogVM) {

    val callLogs = cLogVM.items.collectAsStateWithLifecycle().value

    Column() {

        LazyColumn() {
            itemsIndexed(callLogs) { idx, log ->

            }
        }
    }
}

@Composable
private fun ItemCallLog(){

}