package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.feature.common.PainterImage
import com.unionmobile.angkorlife.feature.common.dpTextUnit
import java.util.Locale

@Composable
fun MainHeader(
    timer: Timer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PainterImage(
            res = R.drawable.img_world_miss_university,
            contentDescription = null
        )

        Timer(
            modifier = Modifier
                .fillMaxWidth(),
            day = timer.days,
            hour = timer.hours,
            minute = timer.minutes,
            second = timer.seconds
        )

        PainterImage(
            res = R.drawable.img_earth,
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        TimeContainerWithLabel(
            time = day,
            label = "DAY"
        )

        Colon()

        TimeContainerWithLabel(
            time = hour,
            label = "HR"
        )

        Colon()

        TimeContainerWithLabel(
            time = minute,
            label = "MIN"
        )

        Colon()

        TimeContainerWithLabel(
            time = second,
            label = "SEC"
        )
    }
}

@Composable
fun TimeContainerWithLabel(
    time: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TimeContainer(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFF1B191A)),
            time = time
        )

        LabelContainer(
            modifier = Modifier
                .size(
                    width = 48.dp,
                    height = 12.dp
                ),
            label = label
        )
    }
}


@Composable
fun TimeContainer(
    time: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String.format(Locale.KOREA, "%02d", time),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 22.dpTextUnit
            ),
            color = Color(0xFFDADADA)
        )
    }
}

@Composable
fun LabelContainer(
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.dpTextUnit
            ),
            color = Color(0xFFB9B9B9)
        )
    }
}

@Composable
fun Colon(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PainterImage(
            modifier = Modifier
                .size(width = 2.dp, height = 10.dp),
            res = R.drawable.img_colon,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TimeContainerPreview() {
    TimeContainer(
        modifier = Modifier
            .size(48.dp)
            .background(Color(0xFF1B191A)),
        time = 10
    )
}

@Preview(showBackground = true)
@Composable
fun LabelContainerPreview() {
    LabelContainer(
        modifier = Modifier
            .size(width = 48.dp, height = 12.dp),
        label = "DAY"
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun TimeContainerWithLabelPreview() {
    TimeContainerWithLabel(
        time = 10,
        label = "SEC"
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
        second = 27,
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun MainHeaderPreview() {
    MainHeader(
        timer = Timer()
    )
}