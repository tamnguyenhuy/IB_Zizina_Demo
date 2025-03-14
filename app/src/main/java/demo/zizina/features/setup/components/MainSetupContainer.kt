package demo.zizina.features.setup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import demo.zizina.features.setup.Footer
import demo.zizina.features.setup.Header
import demo.zizina.types.ComposeColumnContent
import demo.zizina.types.VoidCallback
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun ColumnScope.MainSetupContainer(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    title: String,
    description: String,
    onNext: VoidCallback,
    content: ComposeColumnContent,
) {
    val color = Color.Black
    val hazeStyle = HazeStyle(
        blurRadius = 10.dp,
        backgroundColor = color,
        tint = HazeTint(color.copy(alpha = 0.6f))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .align(Alignment.CenterHorizontally)
            .clip(RoundedCornerShape(10.dp))
            .hazeEffect(state = hazeState, style = hazeStyle)
            .then(modifier)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            title = title,
            subtitle = description
        )

        content.invoke(this)

        Footer(onNext)
    }
}