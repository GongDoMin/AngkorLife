package com.unionmobile.angkorlife.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = false,
        title = "2024 WMU"
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.padding(vertical = 11.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.img_login_top),
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.weight(2f)
            )

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
                            width = 0.5.dp,
                            color = Color(0xFFDBDBDB),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(Color.White)
                    ,
                    value = text,
                    onValueChange = { text = it },
                    placeholder = {
                        Text(
                            text = "Enter your ID",
                            style = TextStyle(
                                color = Color(0xFFAEAEB2),
                                fontFamily = KantumruyFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color(0xFFAEAEB2),
                        fontFamily = KantumruyFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFAEAEB2),
                        unfocusedLabelColor = Color(0xFFAEAEB2),
                        focusedContainerColor = Color(0xFF1B191A),
                        unfocusedContainerColor = Color(0xFF1B191A)
                    )
                )

                Spacer(
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF4232D5),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Unspecified,
                        disabledContentColor = Color.Unspecified,
                    ),
                    onClick = { println("hi!!") },
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(text = "Log in")
                }
            }

            Spacer(
                modifier = Modifier.weight(3f)
            )

            Image(
                painter = painterResource(id = R.drawable.img_login_bottom),
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
        modifier = Modifier
    )
}