package demo.zizina.features.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.SetupViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun SetupPreviewScreen(viewModel: SetupViewModel, onFinish: () -> Unit) {
    val state by viewModel.state.collectAsState()
    SetupContainer { hazeState ->
        SetupHeader("API xstream IPTV Provider", state.language)

        Spacer(Modifier.weight(1f))

        MainContainerPreview(hazeState, onFinish)

        Spacer(Modifier.weight(2f))
    }
}

@Composable
fun ColumnScope.MainContainerPreview(
    hazeState: HazeState,
    onFinish: () -> Unit,
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
            title = "Finish",
            subtitle = "end",
            background = Color.Black.copy(alpha = 0.5f),
            painter = painterResource(R.drawable.ic_api),
            tintColor = Color.Unspecified,
            boxIconColor = Color.Unspecified
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .background(
                    color = Color.Black.copy(0.08f), shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Black.copy(0.08f), shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp))

                Box(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0x3b545454))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_link_2),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .weight(2f)
                        .buttonFocusHighlight(
                            shape = CircleShape
                        ),
                    colors = ButtonDefaults.colors(
                        containerColor = Color.Black.copy(alpha = 0.5f),
                        focusedContainerColor = Color(0x2FFFFFFF)
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
                    onClick = {
                        onFinish()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Continue from start again test",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.White.copy(alpha = 0.96f)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            painter = painterResource(R.drawable.ic_double_arrow_right),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
                Box(modifier = Modifier.weight(1f))
            }
        }
    }
}
