package com.unionmobile.angkorlife.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R

@Composable
fun AngkorLifeTopBarWithContent(
    isBackButtonVisible: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        AngkorLifeTopBar(
            isBackButtonVisible = isBackButtonVisible,
            title = title
        )

        content()
    }
}

@Composable
fun AngkorLifeTopBar(
    isBackButtonVisible: Boolean,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                vertical = 15.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isBackButtonVisible) {
            Image(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(R.drawable.img_top_back),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.img_top_close),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AngkorLifeTopBarWithoutBackButtonPreview() {
    AngkorLifeTopBar(
        isBackButtonVisible = false,
        title = stringResource(R.string.top_bar_title)
    )
}

@Preview(showBackground = true)
@Composable
fun AngkorLifeTopBarWithBackButtonPreview() {
    AngkorLifeTopBar(
        isBackButtonVisible = true,
        title = stringResource(R.string.top_bar_title)
    )
}