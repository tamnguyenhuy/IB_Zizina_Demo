package demo.zizina.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.viewmodel.Language
import demo.zizina.types.OnSelected


@Composable
fun LanguageDropdown(
    shape: Shape = RoundedCornerShape(8.dp),
    background: Color = Color.Gray,
    initValue: Language,
    languages: List<Language> = listOf(
        Language(
            icon = R.drawable.ic_flag_us,
            name = "English",
            short = "EN"
        ),
        Language(
            icon = R.drawable.ic_flag_us,
            name = "English",
            short = "EN"
        ),
        Language(
            icon = R.drawable.ic_flag_us,
            name = "English",
            short = "EN"
        )
    ),
    onSelected: OnSelected<Language>
) {
    var language by remember { mutableStateOf(initValue) }
    var expanded by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier,
        colors = ButtonDefaults.colors(
            focusedContentColor = Color.Black,
            contentColor = Color.White,
        ),
        shape = ButtonDefaults.shape(shape),
        onClick = {
            expanded = !expanded
        },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
    ) {
        Icon(
            painter = painterResource(language.icon),
            contentDescription = "language icon",
            tint = Color.Unspecified,

        )

        Spacer(Modifier.width(4.dp))
        Text(
            text = language.short,
//            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "arrow drop down"
        )
    }


    LanguagePickerDialog(
        showDialog = expanded,
        currentLanguage = language,
        languages = languages,
        onDismiss = {
            expanded = false
        },
        onLanguageSelected = {
            expanded = false
            language = it
            onSelected.invoke(it)
        }
    )
}

@Composable
private fun LanguagePickerDialog(
    showDialog: Boolean,
    currentLanguage: Language,
    languages: List<Language>,
    onDismiss: () -> Unit,
    onLanguageSelected: OnSelected<Language>
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .size(400.dp, 300.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Select Language", color = Color.White, fontSize = 22.sp)


                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(languages) { language ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onLanguageSelected.invoke(language)
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(language.icon),
                                    contentDescription = "language icon",
                                    tint = Color.Unspecified
                                )

                                Text(
                                    text = language.name,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}
