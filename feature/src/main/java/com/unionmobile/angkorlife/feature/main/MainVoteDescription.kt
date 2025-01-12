package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.KantumruyFontFamily

@Composable
fun MainVoteDescription(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "WORLD MISS UNIVERSITY",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = Color(0xFF6F76FF)
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Text(
            text = "Mobile Voting\nInformation",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 29.sp
            ),
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        Text(
            text = "2024 World Miss University brings\ntogether future global leaders who embody both \nbeauty and intellect",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp
            ),
            color = Color(0xFFAEAEB2)
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        VoteDescriptionTable(
            modifier = Modifier
                .background(Color(0xFF1B191A))
                .padding(horizontal = 14.dp)
        )
    }
}

@Composable
fun VoteDescriptionTable(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val rowModifier = remember { Modifier.padding(vertical = 14.dp) }
        val titleModifier = remember { Modifier.weight(1f) }
        val descriptionModifier = remember { Modifier.weight(2f) }

        Row(
            modifier = rowModifier
        ) {
            TitleCell(
                title = "Period",
                modifier = titleModifier
            )

            BasicDescriptionCell(
                description = "10/17(Thu) 12PM - 10/31(Thu) 6PM",
                modifier = descriptionModifier
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFF1E1D1E))
        )

        Row(
            modifier = rowModifier
        ) {
            TitleCell(
                title = "How to vote",
                modifier = titleModifier
            )

            DotWithDescriptionCell(
                descriptions =
                    listOf(
                        "Up to there people can participate in early voting per day",
                        "Three new voting tickets are issued every day at midnight(00:00), and you can vote anew every day during the early voting period"
                    ),
                modifier = descriptionModifier
            )
        }
    }
}

@Composable
fun TitleCell(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        style = TextStyle(
            fontFamily = KantumruyFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 15.sp
        ),
        color = Color(0xFFF6F6F6)
    )
}

@Composable
fun BasicDescriptionCell(
    description: String,
    modifier: Modifier = Modifier,
    dotIsVisible: Boolean = false
) {
    val dotAlpha = remember(dotIsVisible) {
        mutableFloatStateOf(if (dotIsVisible) 1f else 0f)
    }

    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .alpha(dotAlpha.floatValue),
            text = "\u2022",
            color = Color(0xFFDBDBDB),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp
            ),
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Text(
            modifier = modifier,
            text = description,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 15.sp
            ),
            color = Color(0xFFDBDBDB)
        )
    }
}

@Composable
fun DotWithDescriptionCell(
    descriptions: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        descriptions.forEachIndexed { index, description ->
            Column {
                BasicDescriptionCell(
                    description = description,
                    dotIsVisible = true
                )
            }

            if (index != descriptions.lastIndex) {
                Spacer(modifier = Modifier.padding(vertical = 7.dp))
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF1B191A
)
@Composable
fun TitleCellPreview() {
    TitleCell(
        title = "How to vote"
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF1B191A
)
@Composable
fun BasicDescriptionCellPreview() {
    BasicDescriptionCell(
        description = "10/17(Thu) 12PM - 10/31(Thu) 6PM"
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF1B191A
)
@Composable
fun BasicDescriptionCellWithDotPreview() {
    BasicDescriptionCell(
        description = "10/17(Thu) 12PM - 10/31(Thu) 6PM",
        dotIsVisible = true

    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF1B191A
)
@Composable
fun DotWithDescriptionCellPreview() {
    DotWithDescriptionCell(
        descriptions =
            listOf(
                "Up to there people can participate in early voting per day",
                "Three new voting tickets are issued every day at midnight(00:00), and you can vote anew every day during the early voting period"
            )
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF1B191A
)
@Composable
fun VoteDescriptionTablePreview() {
    VoteDescriptionTable()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun MainVoteDescriptionPreview() {
    MainVoteDescription(
        modifier = Modifier
            .padding(16.dp)
    )
}
