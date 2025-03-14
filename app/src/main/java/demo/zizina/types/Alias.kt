package demo.zizina.types

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import dev.chrisbanes.haze.HazeState

typealias ComposeContent = @Composable () -> Unit
typealias ComposeColumnContent = @Composable ColumnScope.() -> Unit
typealias HazeColumnContent = @Composable ColumnScope.(HazeState) -> Unit


typealias VoidCallback = () -> Unit

typealias OnSelected<T> = (T) -> Unit

typealias OnDataChanged<T> = (T) -> Unit

typealias Validator<T> = (T) -> String?