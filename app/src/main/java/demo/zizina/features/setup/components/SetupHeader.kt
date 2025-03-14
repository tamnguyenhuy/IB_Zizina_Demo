package demo.zizina.features.setup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.viewmodel.Language
import demo.zizina.ui.components.LanguageDropdown

@Composable
internal fun SetupHeader(
    title: String,
    language: Language,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.height(24.dp),
            painter = painterResource(R.drawable.ic_logo_with_name),
            contentDescription = "logo_with_name",
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.W700)

        Spacer(modifier = Modifier.weight(1f))

        LanguageDropdown(initValue = language) {

        }

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(onClick = {

        }) {
            Icon(
                painter = painterResource(R.drawable.ic_dashboard),
                contentDescription = "home button"
            )
        }
    }
}
