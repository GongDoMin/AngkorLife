package com.unionmobile.angkorlife.feature.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun PainterImage(
    @DrawableRes res: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = res),
        contentDescription = contentDescription
    )
}