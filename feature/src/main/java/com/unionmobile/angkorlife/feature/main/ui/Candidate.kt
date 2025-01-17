package com.unionmobile.angkorlife.feature.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.dpTextUnit
import com.unionmobile.angkorlife.feature.main.model.toFormattedString

@Composable
fun Candidate(
    url: String,
    id: Int,
    name: String,
    voteCnt: Int,
    voteCntString: String,
    isVoted: Boolean,
    onClickImage: (candidateId: Int, voteCnt: Int) -> Unit,
    onClickVote: (candidateId: Int, voteCnt: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClickImage(id, voteCnt) },
            model = url,
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(vertical = 9.dp))

        Text(
            text = name,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.dpTextUnit,
                lineHeight = 19.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle,
                color = Color(0xFFF6F6F6)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(vertical = 2.dp))

        Text(
            text = voteCntString,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dpTextUnit,
                lineHeight = 16.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle,
                color = Color(0xFF6F76FF)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp),
            enabled = !isVoted,
            colors = ButtonColors(
                containerColor = Color(0xFF4232D5),
                contentColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color(0xFF4232D5),
            ),
            onClick = { onClickVote(id, voteCnt) },
            contentPadding = PaddingValues(
                vertical = 8.dp
            )
        ) {
            val text = if (isVoted) {
                stringResource(R.string.voted)
            } else {
                stringResource(R.string.vote)
            }

            Text(
                text = text,
                style = TextStyle(
                    fontFamily = KantumruyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.dpTextUnit,
                    lineHeight = 16.dpTextUnit,
                    lineHeightStyle = BasicLineHeightStyle,
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun NotVotedCandidatePreview() {
    Candidate(
        url = "",
        id = 0,
        name = "kazakova Julia",
        voteCnt = 10,
        voteCntString = 1200.toFormattedString(),
        isVoted = false,
        onClickImage = { _, _ -> },
        onClickVote = { _, _ -> },
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun VotedCandidatePreview() {
    Candidate(
        url = "",
        id = 0,
        name = "kazakova Julia",
        voteCnt = 10,
        voteCntString = 1200.toFormattedString(),
        isVoted = true,
        onClickImage = { _, _ -> },
        onClickVote = { _, _ ->}
    )
}