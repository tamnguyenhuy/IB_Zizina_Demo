package demo.zizina.features.setup.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SetupViewModel : ViewModel() {
    private val _state = MutableStateFlow(SetupState())
    val state: StateFlow<SetupState> = _state

    fun setDeviceType(type: DeviceType) = setState {
        copy(deviceType = type)
    }

    fun setDeviceSize(size: DeviceSize) = setState {
        copy(deviceSize = size)
    }

    fun setProvider(provider: ProviderType) = setState {
        copy(providerType = provider)
    }

    fun setProviderData(data: IProvider) = setState {
        copy(providerData = data)
    }

    private fun setState(state: SetupState.() -> SetupState) {
        val newState = state.invoke(_state.value)
        _state.tryEmit(newState)
    }
}