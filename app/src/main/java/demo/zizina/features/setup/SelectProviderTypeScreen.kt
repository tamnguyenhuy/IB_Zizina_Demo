package demo.zizina.features.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.ProviderType
import demo.zizina.features.setup.viewmodel.SetupViewModel
import demo.zizina.types.OnSelected
import demo.zizina.types.VoidCallback
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun SelectProviderTypeScreen(viewModel: SetupViewModel, onNext: () -> Unit) {
    val state by viewModel.state.collectAsState()

    SetupContainer { hazeState ->
        Spacer(modifier = Modifier.height(16.dp))

        SetupHeader("Add new IPTV Provider", state.language)

        Spacer(Modifier.weight(1f))

        MainContainer(
            hazeState = hazeState,
            title = "Select the type of provider",
            description = "Select the preferred audio language & sub-titles",
            device = state.providerType,
            sources = ProviderType.entries,
            onSelect = viewModel::setProvider,
            onNext = onNext
        )


        Spacer(Modifier.weight(2f))
    }
}


@Composable
private fun ColumnScope.MainContainer(
    hazeState: HazeState,
    title: String,
    description: String,
    device: ProviderType,
    sources: List<ProviderType>,
    onSelect: OnSelected<ProviderType>,
    onNext: VoidCallback
) {
    val color = Color.Black
    val hazeStyle = HazeStyle(
        blurRadius = 10.dp,
        backgroundColor = color,
        tint = HazeTint(
            color.copy(alpha = 0.6f),
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally)
            .clip(RoundedCornerShape(10.dp))
            .hazeEffect(state = hazeState, style = hazeStyle),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            title = title,
            subtitle = description,
            background = Color(0x3b545454),
            painter = painterResource(R.drawable.ic_social),
            tintColor = Color.Unspecified,
            boxIconColor = Color.Unspecified
        )

        LazyRow(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(sources) {
                ProviderTypeItem(type = it, selected = device == it, onSelect = onSelect)
            }
        }

        Footer(onNext)
    }
}

@Composable
private fun ProviderTypeItem(type: ProviderType, selected: Boolean, onSelect: OnSelected<ProviderType>) {
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .width(100.dp)
            .focusHighlight(
                isSelected = selected,
                shape = RoundedCornerShape(6.dp),
                onFocusChanged = {
                    isFocused = it
                })
            .background(
                color = Color.Black.copy(alpha = 0.6f),
                shape = if (isFocused) RoundedCornerShape(6.dp) else RoundedCornerShape(6.dp)
            )
            .clickable {
                onSelect.invoke(type)
            }
    ) {
        Box(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .height(80.dp),
        ) {
            CheckBox(modifier = Modifier.padding(5.dp), isChecked = selected)

            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(type.getIconRes()),
                contentDescription = "icon provider"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 8.dp, horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.CenterVertically)
        ) {
            Text(
                type.title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.W600,
                lineHeight = 15.sp
            )

            Text(
                text = type.description,
                fontSize = 10.sp,
                color = Color.White,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp
            )
        }
    }
}

