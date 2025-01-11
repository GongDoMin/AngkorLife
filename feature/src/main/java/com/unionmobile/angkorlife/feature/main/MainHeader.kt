package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.PainterImage
import java.util.Locale

@Composable
fun MainHeader(
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PainterImage(
            res = R.drawable.img_login_top,
            contentDescription = null
        )

        Timer(
            day = day,
            hour = hour,
            minute = minute,
            second = second
        )

        PainterImage(
            res = R.drawable.img_login_bottom,
            contentDescription = null
        )
    }
}

@Composable
fun Timer(
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val semiColonModifier = Modifier
            .padding(8.dp)

        Spacer(modifier = Modifier.weight(1f))

        TimeContainerWithLabel(
            label = "DAY",
            time = day
        )

        SemiColon(modifier = semiColonModifier)

        TimeContainerWithLabel(
            label = "HR",
            time = hour
        )

        SemiColon(modifier = semiColonModifier)

        TimeContainerWithLabel(
            label = "MIN",
            time = minute
        )

        SemiColon(modifier = semiColonModifier)

        TimeContainerWithLabel(
            label = "SEC",
            time = second
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun TimeContainerWithLabel(
    label: String,
    time: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeContainer(time = time)

        Spacer(modifier = Modifier.padding(vertical = 2.dp))

        Text(
            text = label,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            ),
            color = Color(0xFFB9B9B9)
        )
    }
}


@Composable
fun TimeContainer(
    time: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color(0xFF1B191A)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String.format(Locale.KOREA, "%02d", time),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp
            ),
            color = Color(0xFFDADADA)
        )
    }
}

@Composable
fun SemiColon(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = ":",
        color = Color(0xFFD9D9D9)
    )
}

@Preview(showBackground = true)
@Composable
fun TimeContainerPreview() {
    TimeContainer(time = 10)
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun TimeContainerWithLabelPreview() {
    TimeContainerWithLabel(
        label = "SEC",
        time = 10
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun TimerPreview() {
    Timer(
        day = 16,
        hour = 0,
        minute = 8,
        second = 27
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun MainHeaderPreview() {
    MainHeader(
        day = 16,
        hour = 0,
        minute = 8,
        second = 27
    )
}