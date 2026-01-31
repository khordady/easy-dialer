package app.arteh.grandpacaller.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import app.arteh.grandpacaller.R
import app.arteh.grandpacaller.ui.theme.AppColor
import app.arteh.grandpacaller.ui.theme.appTypography


@Composable
fun CustomDialogue(
    modifier: Modifier,
    onBack: () -> Unit,
    centerH: Boolean = false,
    content: @Composable () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }

    // Animate the alpha value
    if (!isSystemInDarkTheme()) {
        val alpha by animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(durationMillis = 500),
            label = "alphaAnimation"
        )
        LaunchedEffect(Unit) {
            startAnimation = true
        }
        Spacer(
            Modifier
                .alpha(alpha)
                .fillMaxSize()
                .background(AppColor.LayerBack.resolve())
                .noRippleClickable(onBack)
        )
    }
    BackHandler { onBack() }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = if (centerH) Alignment.CenterHorizontally else Alignment.Start
        ) {
            content()
        }
    }
}

@Composable
fun CustomDropDown(
    expanded: MutableState<Boolean>,
    selectedText: MutableState<String>,
    currentColor: Long = 0xFFDEDEDE,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFDEDEDE),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(5.dp)
            )
            .noRippleClickable { expanded.value = true }
            .padding(10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = selectedText.value)
            Icon(
                painter = painterResource(R.drawable.arrow_down),
                contentDescription = null,
                tint = Color(currentColor)
            )
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            content()
        }
    }
}

@Composable
fun CustomHeader(header: String, goBack: () -> Unit, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = 5.dp)
                .noRippleClickable(goBack),
            painter = painterResource(R.drawable.back),
            contentDescription = "Back",
            tint = color
        )
        Text(
            text = header,
            style = MaterialTheme.appTypography.h1
        )
    }
}

data class PaddingSides(
    val start: Dp,
    val top: Dp,
    val end: Dp,
    val bottom: Dp
)

@Composable
fun EdgePadding(padding: PaddingValues, extra: Dp = 16.dp): PaddingSides {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val edgePad = PaddingSides(
        start = if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            padding.calculateStartPadding(LayoutDirection.Ltr)
        else extra,

        top = padding.calculateTopPadding(),

        end = if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            padding.calculateEndPadding(LayoutDirection.Ltr)
        else extra,

        bottom = padding.calculateBottomPadding()
    )

    return edgePad
}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )

@Composable
fun Divider2(padding: Dp = 5.dp) {
    Spacer(
        Modifier
            .padding(vertical = padding)
            .fillMaxWidth()
            .height(1.dp)
            .background(AppColor.Divider.resolve())
    )
}

@Composable
fun CustomSwitch(desc: String, base: Boolean, onClick: (Boolean) -> Unit) {
    val density = LocalDensity.current

    var checked by remember { mutableStateOf(base) }

    // Animation progress for smooth thumb movement
    val thumbPosition by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        label = "Thumb Animation"
    )

    // Colors
    val trackColor = if (checked) Color(0xFF00CB7A) else Color(0xFFF03B4A)
    val thumbColor = Color.White

    val switchWidth = 60.dp
    val switchHeight = 35.dp
    val thumbSize = 26.dp
    val padding = 4.dp

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = desc)
        Spacer(Modifier.width(30.dp))
        Box(
            modifier = Modifier
                .size(switchWidth, switchHeight)
                .noRippleClickable {
                    onClick(!checked)
                    checked = !checked
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(
                Modifier
                    .size(switchWidth * 3 / 4, switchHeight / 3)
                    .background(trackColor, CircleShape)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .offset { // Move thumb with animation
                        val maxOffset =
                            with(density) { (switchWidth - thumbSize - padding * 2).toPx() }
                        IntOffset((thumbPosition * maxOffset).toInt(), 0)
                    }
                    .padding(padding)
                    .size(thumbSize)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black,
                        spotColor = Color.Black,
                        clip = true
                    )
                    .background(thumbColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = if (checked) painterResource(R.drawable.check)
                    else painterResource(R.drawable.close),
                    contentDescription = null,
                    tint = trackColor
                )
            }
        }
    }
}

@Composable
fun CustomDigButtons(
    okText: String,
    okColor: Color = AppColor.GradGreen.resolve(),
    okClicked: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp)
                .noRippleClickable(onCancel),
            text = "Cancel",
            style = MaterialTheme.appTypography.cancel,
        )
        Text(
            modifier = Modifier
                .widthIn(min = 40.dp)
                .padding(5.dp)
                .noRippleClickable(okClicked),
            text = okText,
            style = MaterialTheme.appTypography.ok,
            color = okColor
        )
    }
}