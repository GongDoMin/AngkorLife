package com.unionmobile.angkorlife.feature.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R

@Composable
fun AngkorLifeTopBarWithContent(
    isBackButtonVisible: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit = {},
    onClickCloseButton: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AngkorLifeTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    vertical = 15.dp,
                    horizontal = 16.dp
                ),
            isBackButtonVisible = isBackButtonVisible,
            title = title,
            onClickBackButton = onClickBackButton,
            onClickCloseButton = onClickCloseButton
        )

        content()
    }
}

@Composable
fun AngkorLifeTopBar(
    isBackButtonVisible: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit = {},
    onClickCloseButton: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PainterImage(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        if (isBackButtonVisible) onClickBackButton()
                    }
                )
                .alpha(if (isBackButtonVisible) 1f else 0f),
            res = R.drawable.img_top_back,
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.dpTextUnit,
                lineHeight = 26.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        PainterImage(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickCloseButton
                ),
            res = R.drawable.img_top_close,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AngkorLifeTopBarWithoutBackButtonPreview() {
    AngkorLifeTopBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                vertical = 15.dp,
                horizontal = 16.dp
            ),
        isBackButtonVisible = false,
        title = stringResource(R.string.top_bar_title)
    )
}

@Preview(showBackground = true)
@Composable
fun AngkorLifeTopBarWithBackButtonPreview() {
    AngkorLifeTopBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                vertical = 15.dp,
                horizontal = 16.dp
            ),
        isBackButtonVisible = true,
        title = stringResource(R.string.top_bar_title)
    )
}