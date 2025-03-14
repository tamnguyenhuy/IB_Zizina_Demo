package demo.zizina.features.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.SetupViewModel
import demo.zizina.types.VoidCallback

@Composable
fun SelectProviderScreen(viewModel: SetupViewModel, onNext: () -> Unit) {
    val state by viewModel.state.collectAsState()

    SetupContainer {
        Spacer(modifier = Modifier.height(16.dp))

        SetupHeader("Add new IPTV Provider", state.language)

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            AddNewProviderButton(onNext)
        }

        Spacer(Modifier.weight(2f))
    }
}

@Composable
private fun AddNewProviderButton(onNext: VoidCallback) {
    Button(
        contentPadding = PaddingValues(0.dp),
        shape = ButtonDefaults.shape(RoundedCornerShape(10.dp)),
        onClick = onNext
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .height(120.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .weight(0.6f)
            ) {
                Image(painter = painterResource(R.drawable.ic_add), contentDescription = "icon add")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .background(Color(0xFF25AAFF)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Add new", fontSize = 20.sp, fontWeight = FontWeight.W600, color = Color.White)
                Text("IPTV Service Provider", fontSize = 12.sp, fontWeight = FontWeight.W400, color = Color.White)
            }
        }
    }
}

