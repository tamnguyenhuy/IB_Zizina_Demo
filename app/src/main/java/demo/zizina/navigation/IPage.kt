package demo.zizina.navigation


interface IPage {
    val route: String
}

interface NonGuard : IPage
interface Guard : IPage

sealed class Auth(override val route: String) : NonGuard {
    data object Login : Auth("login")
}

sealed class Setup(override val route: String) : NonGuard {
    data object DeviceType : Setup("device_type")
    data object DeviceSize : Setup("device_size")
    data object SelectProvider : Setup("provider")
    data object SelectProviderType : Setup("provider_type")
    data object SetupXStreamProvider : Setup("setup_provider")
    data object SetupPreview : Setup("setup_preview")
}


sealed class Main(override val route: String) : Guard {
    data object Home : Main("home")
}