package demo.zizina.features.setup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import demo.zizina.R
import demo.zizina.types.ComposeColumnContent
import demo.zizina.types.HazeColumnContent
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun SetupContainer(content: HazeColumnContent) {
    val hazeState = remember { HazeState() }
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = "background"
        )

        CenterLayer(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.9f)
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(5))
                .border(2.dp, color = Color.White.copy(alpha = 0.2f), RoundedCornerShape(5))
                .padding(16.dp)
        ) {
            content.invoke(this, hazeState)
        }
    }
}


@Composable
private fun CenterLayer(modifier: Modifier, content: ComposeColumnContent) {
    Column(modifier = modifier) {
        content.invoke(this)
    }
}
