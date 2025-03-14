package demo.zizina.features.setup.viewmodel

import androidx.annotation.DrawableRes
import demo.zizina.R

interface BaseItem {
    val title: String
    val description: String
}

enum class DeviceSize(override val title: String, override val description: String) : BaseItem {
    Small("< 32”", "Your device screen is smaller of 32”"),
    Medium("32” to 65”", "Your device screen is 32” to 65”"),
    Large("> 65”", "Your device screen is bigger of 65”")
}

enum class DeviceType(override val title: String, override val description: String) : BaseItem {
    Mobile(title = "Mobile", "Your device is smart phone"),
    TabletOrNotebook(title = "Tablet or Notebook", "Your device is tablet or notebook or PC"),
    TVorOTT(title = "TV or OTT", "Your device is smart TV or OTT"),
}


interface IProvider {
    fun validate(): Boolean
}

data class ApiXStream(
    val category: Category,
    val title: String,
    val username: String,
    val password: String,
    val serverUrl: String,
) : IProvider {

    override fun validate(): Boolean {
        val a = titleError()
        val b = usernameError()
        val c = passwordError()
        val d = serverUrlError()
        return a == null && b == null && c == null && d == null
    }

    fun titleError(): String? {
        return if (title.isBlank()) "The title not filling please typing any"
        else null
    }

    fun usernameError(): String? {
        return if (username.isBlank()) "The username not filling please typing any"
        else null
    }

    fun passwordError(): String? {
        return if (password.isBlank()) "The password not filling please typing any"
        else null
    }

    fun serverUrlError(): String? {
        return if (serverUrl.isBlank()) "The server url not filling please typing any"
        else null
    }


    enum class Category(val title: String) {
        FootballTrophy("Football Trophy"),
        ;

        @DrawableRes
        fun getIconRes(): Int = when (this) {
            FootballTrophy -> R.drawable.ic_football_trophy
        }
    }
}

enum class ProviderType(override val title: String, override val description: String) : BaseItem {
    Local("Local", "Movies and series you have stored on your local network."),
    ApiXStream("API xstream", "From a caring IPTV provider who uses the Xtream API protocol."),
    FreeTv(
        "Free TV (GR+)",
        "Watch free TV channels from your country (Greece) and from all over the world."
    ),
    MPU(
        "List MPU",
        "Watch free TV channels from your country (Greece) and from all over the world."
    ), ;

    @DrawableRes
    fun getIconRes(): Int = when (this) {
        Local -> R.drawable.ic_local_network
        ApiXStream -> R.drawable.ic_api
        FreeTv -> R.drawable.ic_tv_news
        MPU -> R.drawable.ic_playlist
    }
}

data class SetupState(
    val language: Language = Language(
        icon = R.drawable.ic_flag_us,
        name = "English",
        short = "EN"
    ),
    val deviceType: DeviceType = DeviceType.TVorOTT,
    val deviceSize: DeviceSize = DeviceSize.Medium,
    val providerType: ProviderType = ProviderType.ApiXStream,
    val providerData: IProvider = ApiXStream(
        category = ApiXStream.Category.FootballTrophy,
        title = "",
        username = "",
        password = "",
        serverUrl = ""
    ),
) {

}