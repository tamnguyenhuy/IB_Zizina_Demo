package demo.zizina.features.setup.viewmodel

import androidx.annotation.DrawableRes

data class Language(
    @DrawableRes val icon: Int,
    val short: String,
    val name: String,
)

