package demo.zizina.features.setup

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import demo.zizina.features.setup.components.MainSetupContainer
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.DeviceSize
import demo.zizina.features.setup.viewmodel.DeviceType
import demo.zizina.features.setup.viewmodel.SetupViewModel

@Composable
fun DeviceSizeScreen(viewModel: SetupViewModel, onNext: () -> Unit) {
    val state by viewModel.state.collectAsState()
    SetupContainer { hazeState ->
        Spacer(modifier = Modifier.height(16.dp))

        SetupHeader("Your device is: ${state.deviceType.title}", state.language)

        Spacer(Modifier.weight(1f))

        MainSetupContainer(
            hazeState = hazeState,
            title = "Device",
            description = "Select your device type",
            onNext = onNext
        ) {
            LazyColumn {
                items(DeviceSize.entries) {
                    DeviceTypeItem(it, selected = state.deviceSize == it, viewModel::setDeviceSize)
                }
            }
        }

        Spacer(Modifier.weight(2f))
    }
}