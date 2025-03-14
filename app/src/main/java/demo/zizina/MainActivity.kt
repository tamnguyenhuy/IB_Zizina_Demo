package demo.zizina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Text
import demo.zizina.navigation.AppNavHost
import demo.zizina.ui.theme.ZizinaDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ZizinaDemoTheme {
                CompositionLocalProvider(LocalDensity provides Density(
                    density = 2f,
                    fontScale = 0.7f
                )) {
                    AppNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun rememberFigmaDensity(
    figmaWidthPx: Float = 1920f, // Default Figma design width
    figmaHeightPx: Float = 1080f  // Default Figma design height
): Density {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Get real device width & height in pixels
    val screenWidthPx = with(density) { configuration.screenWidthDp * density.density }
    val screenHeightPx = with(density) { configuration.screenHeightDp * density.density }



    // Calculate scale based on width & height ratios
    val widthScale = screenWidthPx / figmaWidthPx
    val heightScale = screenHeightPx / figmaHeightPx

    // Use the smaller scale to maintain aspect ratio
    val scaleFactor = minOf(widthScale, heightScale)

    // Override LocalDensity with computed font scale
    return remember(density) {
        Density(density.density, fontScale = scaleFactor)
    }
}

@Stable
fun Dp.toPxf(density: Density): Float = with(density) { this@toPxf.toPx() }
@Stable
@Composable
fun Dp.toPxf(): Float = toPxf(LocalDensity.current)


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val figmaDensity = rememberFigmaDensity()
    println("figma density = $figmaDensity")

    ZizinaDemoTheme {
        CompositionLocalProvider(LocalDensity provides figmaDensity) {
            AppNavHost(navController)
        }
    }
}