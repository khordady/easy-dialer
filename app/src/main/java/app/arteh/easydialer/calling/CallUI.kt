package app.arteh.easydialer.calling

import android.telecom.Call
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.arteh.easydialer.R
import app.arteh.easydialer.calling.models.CallState
import app.arteh.easydialer.ui.noRippleClickable

private val LocalVM = staticCompositionLocalOf<CallVM> {
    error("CallVM not provided")
}

@Composable
fun CallScreen(vm: CallVM = viewModel()) {
    CompositionLocalProvider(LocalVM provides vm) {
        CallContent()
    }
}

@Composable
fun CallContent() {
    val callVM = LocalVM.current
    val uiState = callVM.uiState.collectAsStateWithLifecycle().value

    when (uiState.state) {
        CallState.Incoming -> IncomingCallUI(uiState.phoneNumber, uiState.name)
        CallState.Calling -> {}
    }
}

@Composable
fun IncomingCallUI(number: String, name: String) {
    val callVM = LocalVM.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            fontSize = 32.sp
        )

        Spacer(Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            BigCallButton(
                "ANSWER", R.drawable.call,
                Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .height(100.dp)
                    .background(Color.Green, RoundedCornerShape(5.dp))
                    .noRippleClickable(callVM::answer),
            )

            BigCallButton(
                "HANG UP", R.drawable.call_end,
                Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .height(100.dp)
                    .background(Color.Red, RoundedCornerShape(5.dp))
                    .noRippleClickable(callVM::reject)
            )
        }
    }
}

@Composable
fun OngoingCallUI(call: Call) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text("Call in Progress", fontSize = 40.sp)
//
//        BigCallButton("HANG UP", Color.Red) {
//            call.disconnect()
//        }
//    }
}

@Composable
fun BigCallButton(
    text: String,
    icon: Int,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.White
        )
        Text(text = text, color = Color.White)
    }
}