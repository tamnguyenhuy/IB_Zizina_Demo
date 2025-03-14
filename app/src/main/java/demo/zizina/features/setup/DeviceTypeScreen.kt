package demo.zizina.features.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.components.MainSetupContainer
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.BaseItem
import demo.zizina.features.setup.viewmodel.DeviceType
import demo.zizina.features.setup.viewmodel.SetupViewModel
import demo.zizina.navigation.LocalNavController
import demo.zizina.navigation.canBack
import demo.zizina.types.OnSelected
import demo.zizina.types.VoidCallback

@Composable
fun DeviceTypeScreen(
    viewModel: SetupViewModel,
    onNext: VoidCallback,
) {
    val state by viewModel.state.collectAsState()
    SetupContainer { hazeState ->
        Spacer(modifier = Modifier.height(16.dp))

        SetupHeader("Device", state.language)

        Spacer(Modifier.weight(1f))

        MainSetupContainer(
            hazeState = hazeState,
            title = "Device",
            description = "Select your device type",
            onNext = onNext
        ) {
            LazyColumn {
                items(DeviceType.entries) {
                    DeviceTypeItem(it, selected = state.deviceType == it, viewModel::setDeviceType)
                }
            }
        }

        Spacer(Modifier.weight(2f))
    }
}


@Composable
fun <T : BaseItem> DeviceTypeItem(type: T, selected: Boolean, onSelect: OnSelected<T>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .focusHighlight(isSelected = selected)
            .clickable {
                onSelect.invoke(type)
            }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CheckBox(isChecked = selected)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(type.title, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.W500)
            Text(
                type.description,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.W400
            )
        }
    }
}

@Composable
fun CheckBox(modifier: Modifier = Modifier, isChecked: Boolean) {
    Box(
        modifier = modifier
            .size(17.dp)
            .clip(CircleShape)
            .background(if (isChecked) Color(0xFFF9FAFB) else Color.Black)
            .run {
                if (isChecked) {
                    border(4.dp, color = Color(0xFF45A560), shape = CircleShape)
                } else {
                    this
                }
            }
    )
}


@Composable
fun Header(
    title: String,
    subtitle: String,
    background: Color = Color.Black.copy(alpha = 0.5f),
    painter: Painter = painterResource(R.drawable.ic_device),
    tintColor: Color = Color.White,
    boxIconColor: Color = Color(0x0FFFFFFF)
) {
    val navController = LocalNavController.current
    val canBack = navController.canBack()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (canBack) {
            IconButton(
                colors = ButtonDefaults.colors(
                    containerColor = Color(0x0FFFFFFF),
                    focusedContainerColor = Color(0x2FFFFFFF),
                    contentColor = Color.White,
                    focusedContentColor = Color.White
                ),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
            }
        }

        Column {
            Text(title, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.W700)
            Text(
                subtitle,
                color = Color(0xFF9D9D9D),
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(boxIconColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painter,
                contentDescription = "",
                tint = tintColor
            )
        }
    }
}


@Composable
fun Footer(onNext: VoidCallback) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.buttonFocusHighlight(
                shape = CircleShape
            ),
            colors = ButtonDefaults.colors(
                containerColor = Color(0x67000000),
                focusedContainerColor = Color(0x2FFFFFFF)
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 4.dp),
            onClick = onNext
        ) {
            Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.W600, color = Color.White.copy(alpha = 0.96f))
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.ic_double_arrow_right),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Modifier.focusHighlight(
    isSelected: Boolean = false,
    shape: Shape = RectangleShape,
    onFocusChanged: (Boolean) -> Unit = { }
): Modifier {
    var isFocused by remember { mutableStateOf(false) }
    return this
        .onFocusChanged {
            isFocused = it.isFocused
            onFocusChanged(it.isFocused)
        }
        .focusable()
        .background(
            if (isFocused || isSelected) {
                Color.White.copy(alpha = 0.25f)
            } else {
                Color.Transparent
            },
            shape = shape
        )
        .run {
            if (isFocused) {
                border(2.dp, color = Color(0xFF32d74b), shape = shape)
            } else {
                this
            }
        }
}

@Composable
fun Modifier.buttonFocusHighlight(
    isSelected: Boolean = false,
    shape: Shape = RectangleShape
): Modifier {
    var isFocused by remember { mutableStateOf(false) }
    return this
        .onFocusChanged {
            isFocused = it.isFocused
        }
        .focusable()
        .background(
            if (isFocused || isSelected) {
                Color(0xFF45A560)
            } else {
                Color.Transparent
            },
            shape = shape
        )
}