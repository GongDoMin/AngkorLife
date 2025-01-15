package com.unionmobile.angkorlife.feature.main.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.dpTextUnit

@Composable
fun MainVoteDescription(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.world_miss_university),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.dpTextUnit
            ),
            color = Color(0xFF6F76FF)
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Text(
            text = stringResource(R.string.mobile_voting_information),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.dpTextUnit,
                lineHeight = 29.dpTextUnit
            ),
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        Text(
            text = stringResource(R.string.world_miss_university_description),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.dpTextUnit,
                lineHeight = 22.dpTextUnit
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
                title = stringResource(R.string.period),
                modifier = titleModifier
            )

            BasicDescriptionCell(
                description = stringResource(R.string.period_description),
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
                title = stringResource(R.string.how_to_vote),
                modifier = titleModifier
            )

            DotWithDescriptionCell(
                descriptions =
                    listOf(
                        stringResource(R.string.how_to_vote_description_1),
                        stringResource(R.string.how_to_vote_description_2)
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
            fontSize = 13.dpTextUnit,
            lineHeight = 15.dpTextUnit,
            lineHeightStyle = BasicLineHeightStyle,
            color = Color(0xFFF6F6F6)
        )
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
            text = stringResource(R.string.dot),
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.dpTextUnit,
                lineHeight = 15.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle,
                color = Color(0xFFDBDBDB)
            )
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Text(
            modifier = modifier,
            text = description,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.dpTextUnit,
                lineHeight = 15.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle,
                color = Color(0xFFDBDBDB)
            )
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
