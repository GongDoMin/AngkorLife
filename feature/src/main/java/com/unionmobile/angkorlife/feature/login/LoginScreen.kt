package com.unionmobile.angkorlife.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.EventCollect
import com.unionmobile.angkorlife.feature.common.PainterImage
import com.unionmobile.angkorlife.feature.common.dpTextUnit

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    showSnackBar: (message: String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberScrollState()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(keyboardHeight) {
        scrollState.scrollBy(keyboardHeight.toFloat())
    }

    EventCollect(
        event = viewModel.event,
        lifecycleOwner = lifecycleOwner,
    ) {
        when (it) {
            is LoginViewModel.Event.SuccessLogin -> navigateToMain()
            is LoginViewModel.Event.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = false,
        title = stringResource(R.string.top_bar_title)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 11.dp))

            PainterImage(
                res = R.drawable.img_world_miss_university,
                contentDescription = null
            )

            Spacer(modifier = Modifier.weight(2f))

            Column(
                modifier = Modifier
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDBDBDB),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = uiState.id,
                    onValueChange = viewModel::updateId,
                    placeholder = {
                        Text(
                            text = "Enter your ID",
                            style = TextStyle(
                                color = Color(0xFFAEAEB2),
                                fontFamily = KantumruyFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.dpTextUnit
                            )
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color(0xFFAEAEB2),
                        fontFamily = KantumruyFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.dpTextUnit
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = Color(0xFFAEAEB2),
                        focusedTextColor = Color(0xFFAEAEB2),
                        unfocusedPlaceholderColor = Color(0xFFAEAEB2),
                        focusedPlaceholderColor = Color(0xFFAEAEB2),
                        focusedContainerColor = Color(0xFF1B191A),
                        unfocusedContainerColor = Color(0xFF1B191A),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(8.dp),

                )

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF4232D5),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFF4232D5),
                        disabledContentColor = Color.White,
                    ),
                    onClick = viewModel::login,
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = "Log in",
                        style = TextStyle(
                            fontFamily = KantumruyFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.dpTextUnit
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(3f))

            PainterImage(
                res = R.drawable.img_earth,
                contentDescription = null
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navigateToMain = {},
        showSnackBar = {}
    )
}