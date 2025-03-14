package demo.zizina.features.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import demo.zizina.R
import demo.zizina.features.setup.components.MainSetupContainer
import demo.zizina.features.setup.components.SetupContainer
import demo.zizina.features.setup.components.SetupHeader
import demo.zizina.features.setup.viewmodel.ApiXStream
import demo.zizina.features.setup.viewmodel.SetupViewModel
import demo.zizina.types.OnDataChanged
import demo.zizina.types.OnSelected
import demo.zizina.types.Validator

@Composable
fun SetupXStreamProviderScreen(viewModel: SetupViewModel, onNext: () -> Unit) {
    val state by viewModel.state.collectAsState()
    require(state.providerData is ApiXStream)
    var showError by remember { mutableStateOf(false) }

    SetupContainer { hazeState ->
        SetupHeader("Add new IPTV Provider", state.language)

        MainSetupContainer(
            modifier = Modifier,
            hazeState = hazeState,
            title = "Edit API xstream IPTV Provider",
            description = "Select the preferred audio language & sub-titles",
            onNext = {
                if (state.providerData.validate()) {
                    onNext.invoke()
                } else {
                    showError = true
                }
            }
        ) {
            InputForm(
                showError = showError,
                data = state.providerData as ApiXStream,
                onDataChanged = viewModel::setProviderData
            )
        }
    }
}

@Composable
private fun InputForm(
    data: ApiXStream,
    showError: Boolean,
    onDataChanged: OnDataChanged<ApiXStream>
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black.copy(0.2f))
            .padding(8.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            InputField(
                modifier = Modifier.weight(1f),
                leadingIcon = R.drawable.ic_stream_title,
                value = data.title,
                showError = showError,
                validator = { data.titleError() },
                placeholder = "Typing any title"
            ) {
                onDataChanged.invoke(data.copy(title = it))
            }

            CategoryDropdown(
                modifier = Modifier.height(30.dp),
                source = ApiXStream.Category.entries,
                value = data.category,
            ) {
                onDataChanged.invoke(data.copy(category = it))
            }
        }
        Divider()
        InputField(
            modifier = Modifier,
            leadingIcon = R.drawable.ic_user_name,
            value = data.username,
            showError = showError,
            validator = { data.usernameError() },
            placeholder = "Username"
        ) {
            onDataChanged.invoke(data.copy(username = it))
        }
        Divider()
        InputField(
            modifier = Modifier,
            leadingIcon = R.drawable.ic_password,
            value = data.password,
            showError = showError,
            validator = { data.passwordError() },
            placeholder = "Password"
        ) {
            onDataChanged.invoke(data.copy(password = it))
        }
        Divider()
        InputField(
            modifier = Modifier,
            leadingIcon = R.drawable.ic_link,
            value = data.serverUrl,
            showError = showError,
            validator = { data.serverUrlError() },
            help = "example: https://stream.yourprovider.com",
            placeholder = "Server URL"
        ) {
            onDataChanged.invoke(data.copy(serverUrl = it))
        }
    }
}

@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    leadingIcon: Int,
    value: String,
    placeholder: String = "",
    help: String = "",
    showError: Boolean = false,
    validator: Validator<String>? = null,
    onValueChanged: OnDataChanged<String>
) {
    var isFocused by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(value) }
    var shouldShowError by remember(showError) { mutableStateOf(showError) }
    val error = validator?.invoke(state)?.takeIf { shouldShowError }

    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0x3b545454))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = "",
                tint = Color.White
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            BasicTextField(
                value = state,
                onValueChange = {
                    state = it
                    onValueChanged.invoke(it)
                    shouldShowError = false
                },
                textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = when {
                            isFocused -> Color.Green
                            error != null -> Color.Red
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                decorationBox = { innerTextField ->
                    if (placeholder.isNotBlank() && state.isEmpty()) {
                        Text(placeholder, color = Color.LightGray)
                    }
                    innerTextField.invoke()
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )

            when {
                error != null -> Text(error, color = Color.Red, fontSize = 14.sp)
                help.isNotBlank() -> Text(help, color = Color.LightGray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun CategoryDropdown(
    modifier: Modifier = Modifier,
    source: List<ApiXStream.Category>,
    value: ApiXStream.Category,
    onSelected: OnSelected<ApiXStream.Category>
) {
    var showDropDown by remember { mutableStateOf(false) }
    Button(
        modifier = modifier,
        colors = ButtonDefaults.colors(
            focusedContentColor = Color.Black,
            contentColor = Color.White,
        ),
        shape = ButtonDefaults.shape(RoundedCornerShape(50)),
        onClick = {
            showDropDown = !showDropDown
        },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
    ) {
        Image(
            painter = painterResource(value.getIconRes()),
            contentDescription = "icon"
        )
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = if (showDropDown) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "arrow drop down"
        )
    }

    if (showDropDown) {
        Dialog(onDismissRequest = {
            showDropDown = false
        }) {
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
                    Text("Select Category", color = Color.White, fontSize = 22.sp)


                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(source) { category ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSelected.invoke(category)
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(category.getIconRes()),
                                    contentDescription = "category icon",
                                    tint = Color.Unspecified
                                )

                                Text(
                                    text = category.title,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Button(onClick = {
                        showDropDown = false
                    }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
fun Divider(indent: Dp = 0.dp, endIndent: Dp = 0.dp) {
    Box(
        Modifier
            .padding(start = indent, end = endIndent)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray.copy(0.2f))
    )
}